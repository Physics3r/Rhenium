package net.minecraft.client.renderer.chunk;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;

public class ChunkCompileTaskGenerator {
    @Getter
    private final RenderChunk renderChunk;
    @Getter
    private final ReentrantLock lock = new ReentrantLock();
    private final List<Runnable> listFinishRunnables = Lists.<Runnable>newArrayList();
    @Getter
    private final ChunkCompileTaskGenerator.Type type;
    @Setter
    @Getter
    private RegionRenderCacheBuilder regionRenderCacheBuilder;
    @Setter
    @Getter
    private CompiledChunk compiledChunk;
    @Getter
    private ChunkCompileTaskGenerator.Status status = ChunkCompileTaskGenerator.Status.PENDING;
    @Getter
    private boolean finished;

    public ChunkCompileTaskGenerator(RenderChunk renderChunkIn, ChunkCompileTaskGenerator.Type typeIn) {
        this.renderChunk = renderChunkIn;
        this.type = typeIn;
    }

    public void setStatus(ChunkCompileTaskGenerator.Status statusIn) {
        this.lock.lock();

        try {
            this.status = statusIn;
        } finally {
            this.lock.unlock();
        }
    }

    public void finish() {
        this.lock.lock();

        try {
            if (this.type == ChunkCompileTaskGenerator.Type.REBUILD_CHUNK && this.status != ChunkCompileTaskGenerator.Status.DONE) {
                this.renderChunk.setNeedsUpdate(true);
            }

            this.finished = true;
            this.status = ChunkCompileTaskGenerator.Status.DONE;

            for (Runnable runnable : this.listFinishRunnables) {
                runnable.run();
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void addFinishRunnable(Runnable p_178539_1_) {
        this.lock.lock();

        try {
            this.listFinishRunnables.add(p_178539_1_);

            if (this.finished) {
                p_178539_1_.run();
            }
        } finally {
            this.lock.unlock();
        }
    }

    public static enum Status {
        PENDING,
        COMPILING,
        UPLOADING,
        DONE;
    }

    public static enum Type {
        REBUILD_CHUNK,
        RESORT_TRANSPARENCY;
    }
}
