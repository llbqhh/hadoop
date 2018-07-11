package org.llbqhh;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.security.UserGroupInformation;
import org.junit.Test;

import java.io.IOException;
import java.security.PrivilegedAction;

public class TestFsPermission {
    @Test
    public void testCatFile() {
        UserGroupInformation ugi = UserGroupInformation.createRemoteUser("llbqhh");
        final String filePath = "/llbtest/core-site.xml";
        String returnResult = ugi.doAs(new PrivilegedAction<String>() {
            @Override
            public String run() {
                    FSDataInputStream in = null;
                    FileSystem fs = null;
            System.out.println((String.format("file cat:%s", filePath)));
            try {
                        fs = FileSystem.get(getConf());
                        in = fs.open(new Path(filePath));
                        byte[] buffer = new byte[in.available()];
                        in.readFully(0, buffer);
                        return new String(buffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return e.getMessage();
                    }
            finally {
                        IOUtils.closeStream(in);
                        try {
                            if (fs != null) {
                                fs.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        });
        System.out.println(returnResult);
    }

    private Configuration getConf() {
        Configuration configuration = new Configuration();
        configuration.addResource(new Path("file:///export/App/hadoop-2.6.2/etc/hadoop/core-site.xml"));
        configuration.addResource(new Path("file:///export/App/hadoop-2.6.2/etc/hadoop/hdfs-site.xml"));
        return configuration;
    }
}
