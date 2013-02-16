package profiler;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * Arkady Shagal
 * 6:36
 */
public class CpuProfiler {
    private static Sigar sigar;

    public CpuProfiler() throws SigarException {
        sigar = new Sigar();
        Cpu cpu = sigar.getCpu();
        System.err.println(cpu.getIdle());
        System.err.println(cpu.getNice());
        System.err.println(cpu.getTotal());
        System.err.println(cpu.getStolen());
    }
}
