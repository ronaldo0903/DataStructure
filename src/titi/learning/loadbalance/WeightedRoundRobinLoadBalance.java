package titi.learning.loadbalance;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class WeightedRoundRobinLoadBalance {
    protected static class WeightedRoundRobin {
        private int weight;
        private AtomicLong current = new AtomicLong(0);
        public void setWeight(int weight) {
            this.weight = weight;
            current.set(0);
        }
        public long increaseCurrent() {
            return current.addAndGet(weight);
        }
        public void decreaseBy(int total) {
            current.addAndGet(-1 * total);
        }
    }
    private ConcurrentMap<String, WeightedRoundRobin> serverWeightedMap = new ConcurrentHashMap<String, WeightedRoundRobin>();
    private Map<String, Integer> serverWeightOrigMap = new HashMap<>();
    public void setWeightMap(Map<String, Integer> map) {
        this.serverWeightOrigMap.putAll(map);
    }
    public String selectServer() {
        int totalWeight = 0;
        long maxCurrent = Long.MIN_VALUE;
        String selectedServer = null;
        WeightedRoundRobin selectedWRR = null;
        for(Map.Entry<String, Integer> entry : serverWeightOrigMap.entrySet()) {
            String curServer = entry.getKey();
            WeightedRoundRobin wRR = serverWeightedMap.get(curServer);
            if(wRR == null) {
                wRR = new WeightedRoundRobin();
                wRR.setWeight(serverWeightOrigMap.get(curServer));
                serverWeightedMap.putIfAbsent(curServer, wRR);
            }
            long cur = wRR.increaseCurrent();
            if(cur > maxCurrent) {
                maxCurrent = cur;
                selectedServer = curServer;
                selectedWRR = wRR;
            }
            totalWeight += entry.getValue();
        }
        if(selectedServer != null) {
            selectedWRR.decreaseBy(totalWeight);
            return selectedServer;
        }
        return serverWeightOrigMap.keySet().toArray()[0].toString();
    }

    public static void main(String[] args) {
        Map<String, Integer> serverWeights = new HashMap<>();
        serverWeights.put("A", 5);
        serverWeights.put("B", 3);
        serverWeights.put("C", 2);
        WeightedRoundRobinLoadBalance wrrLoadBalance = new WeightedRoundRobinLoadBalance();
        wrrLoadBalance.setWeightMap(serverWeights);
        for(int i=1; i<=20; i++) {
            System.out.println("第" + i + "次选择的server是：" + wrrLoadBalance.selectServer());
        }

    }
}
