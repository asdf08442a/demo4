package com.enterprise.demo.sys.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

/**
 * 获取IP
 */
public class IpUtils {

  /**
   * 获取远程IP
   */
  public static String getIpAddrByRequest(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  /**
   * @return 获取本机IP
   */
  public static String getRealIp() throws SocketException {
    String localip = null;// 本地IP，如果没有配置外网IP则返回它
    String netip = null;// 外网IP

    Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
    InetAddress ip;
    boolean finded = false;// 是否找到外网IP
    while (netInterfaces.hasMoreElements() && !finded) {
      NetworkInterface ni = netInterfaces.nextElement();
      Enumeration<InetAddress> address = ni.getInetAddresses();
      while (address.hasMoreElements()) {
        ip = address.nextElement();
        if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress()
            .contains(":")) {
          // 外网IP
          netip = ip.getHostAddress();
          finded = true;
          break;
        } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress()
            .contains(":")) {
          // 内网IP
          localip = ip.getHostAddress();
        }
      }
    }

    if (netip != null && !"".equals(netip)) {
      return netip;
    } else {
      return localip;
    }
  }

}