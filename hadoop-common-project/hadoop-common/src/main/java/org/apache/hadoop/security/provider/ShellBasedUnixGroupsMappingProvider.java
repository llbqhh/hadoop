package org.apache.hadoop.security.provider;

import org.apache.hadoop.security.ShellBasedUnixGroupsMapping;

/**
 * 直接继承ShellBasedUnixGroupsMapping，方便联合自己的provider使用时兼容旧的实现（从namenode操作系统取得用户组信息）
 */
public class ShellBasedUnixGroupsMappingProvider extends ShellBasedUnixGroupsMapping {
}
