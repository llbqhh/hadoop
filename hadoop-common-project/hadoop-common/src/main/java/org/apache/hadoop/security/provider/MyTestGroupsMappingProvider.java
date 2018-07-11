package org.apache.hadoop.security.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.security.GroupMappingServiceProvider;
import org.apache.hadoop.security.ShellBasedUnixGroupsMapping;

import java.io.IOException;
import java.util.*;

/**
 * 测试类，给予指定账户指定的组信息
 */
public class MyTestGroupsMappingProvider implements GroupMappingServiceProvider {
    private static final Log LOG =
            LogFactory.getLog(MyTestGroupsMappingProvider.class);
    //将用户组信息存储在内存中
    static Map<String, List<String>> userGroupsMap;

    static {
        userGroupsMap = new HashMap<String, List<String>>();
        userGroupsMap.put("llb", Arrays.asList("llb", "llbqhh", "hadoop"));
        userGroupsMap.put("qhh", Arrays.asList("qhh", "llbqhh"));
        userGroupsMap.put("test", Arrays.asList("test", "testGroup"));
    }

    @Override
    public List<String> getGroups(String user) throws IOException {
        List groups = userGroupsMap.getOrDefault(user, new ArrayList<>());
        LOG.info("getGroups %s %s " + user);
      return groups;
    }

    @Override
    public void cacheGroupsRefresh() throws IOException {
      // does nothing in this provider of user to groups mapping
    }

    @Override
    public void cacheGroupsAdd(List<String> groups) throws IOException {
      // does nothing in this provider of user to groups mapping
    }
}
