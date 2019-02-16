package com.tyilack.assist;

import org.junit.Test;

public class RuntimeTest {

    @Test
    public void testProgramRun() throws Exception {
        Runtime.getRuntime().exec("C:\\Program Files (x86)\\NetDragon\\魔域-箭破天穹\\AutoPatch.exe");
    }

}
