package com.zyouke.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * 单实例测试
 */
public class SimpleCanalTest {

    private static final Logger LOGGER  = LoggerFactory.getLogger(SimpleCanalTest.class);

    private static final int    CANAL_BATCH_SIZE = 1000;

    public static void main(String args[]) {
        String confName = "example";
        String canalUsername = "";
        String canalPassword = "";
        // 每次数据的偏移量
        long batchId = 0;
        String destination = "example";
        String ip = "122.114.90.68";
        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(ip, 11111),
                destination,
                canalUsername,
                canalPassword);
        while (true) {
            try {
                connector.connect();
                connector.subscribe("zyouke\\\\..*");
                connector.rollback();
                // 内层死循环:
                // 按频率实时监听数据变化，
                // 一旦收到变化数据，立即做消费处理，并ack
                // 考虑消费速度，可以考虑异步处理，并ack
                while (true) {
                    // 获取指定数量的数据
                    Message message = connector.getWithoutAck(CANAL_BATCH_SIZE);
                    batchId = message.getId();
                    int size = message.getEntries().size();
                    // 偏移量不等于-1 或者 获取的数据条数不为0 时，认为拿到消息，并处理
                    if (batchId == -1 || size == 0) {
                        // 200ms 拉一次变动数据
                        Thread.sleep(2000);
                        connector.ack(batchId); // 提交确认
                    } else {
                        System.out.println("----------->" + message.getEntries());
                        connector.ack(batchId); // 提交确认
                    }

                }

            } catch (Exception e) {
                LOGGER.error("read canal message error , exception : ", e);
                // 处理失败, 按偏移量回滚数据
                connector.rollback(batchId);
            } finally {
                // 关闭连接
                connector.disconnect();
            }
        }
    }
}
