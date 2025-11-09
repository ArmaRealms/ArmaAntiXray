package com.vanillage.raytraceantixray.data;

import com.vanillage.raytraceantixray.net.DuplexPacketHandler;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

public final class PlayerData implements Callable<Object> {

    private final ConcurrentMap<LongWrapper, ChunkBlocks> chunks = new ConcurrentHashMap<>();
    private final Queue<Result> results = new ConcurrentLinkedQueue<>();
    private Callable<?> callable;
    private DuplexPacketHandler packetHandler;
    private volatile VectorialLocation[] locations;

    public PlayerData(VectorialLocation[] locations) {
        this.locations = locations;
    }

    public VectorialLocation[] getLocations() {
        return locations;
    }

    public void setLocations(VectorialLocation[] locations) {
        this.locations = locations;
    }

    public ConcurrentMap<LongWrapper, ChunkBlocks> getChunks() {
        return chunks;
    }

    public Queue<Result> getResults() {
        return results;
    }

    public Callable<?> getCallable() {
        return callable;
    }

    public void setCallable(Callable<?> callable) {
        this.callable = callable;
    }

    public DuplexPacketHandler getPacketHandler() {
        return packetHandler;
    }

    public void setPacketHandler(DuplexPacketHandler packetHandler) {
        this.packetHandler = packetHandler;
    }

    @Override
    public Object call() throws Exception {
        return callable.call();
    }

}
