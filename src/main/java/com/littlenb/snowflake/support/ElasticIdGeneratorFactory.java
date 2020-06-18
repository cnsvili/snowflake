package com.littlenb.snowflake.support;

import com.littlenb.snowflake.worker.WorkerIdAssigner;
import com.littlenb.snowflake.sequence.IdGenerator;
import com.littlenb.snowflake.sequence.SnowFlakeGenerator;
import java.util.concurrent.TimeUnit;

/**
 * <pre>{@code
 * +------+----------------------+----------------+-----------+
 * | sign |  delta millisecond   | worker node id | sequence  |
 * +------+----------------------+----------------+-----------+
 *   1bit       timeBits             workerBits      seqBits
 * }</pre>
 *
 * <b>Note that:</b> The total bits must be 64 -1
 *
 * @author sway.li
 **/
public class ElasticIdGeneratorFactory implements IdGeneratorFactory {

    /**
     * Bits allocate
     */
    protected int timeBits;
    protected int workerBits;
    protected int seqBits;

    /**
     * epoch timestamp
     */
    protected long epochTimestamp;

    protected TimeUnit timeUnit;

    public ElasticIdGeneratorFactory() {
    }

    @Override
    public IdGenerator create(WorkerIdAssigner workerIdAssigner) {
        return create(workerIdAssigner.assignWorkerId());
    }

    @Override
    public IdGenerator create(long workerId) {
        return new SnowFlakeGenerator(timeUnit, timeBits, workerBits, seqBits, epochTimestamp, workerId);
    }

    /* setter */

    public void setTimeBits(int timeBits) {
        this.timeBits = timeBits;
    }

    public void setWorkerBits(int workerBits) {
        this.workerBits = workerBits;
    }

    public void setSeqBits(int seqBits) {
        this.seqBits = seqBits;
    }

    public void setEpochTimestamp(long epochTimestamp) {
        this.epochTimestamp = epochTimestamp;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

}
