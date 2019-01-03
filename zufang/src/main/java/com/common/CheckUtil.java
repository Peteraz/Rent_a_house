/**   
 *  Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
* @Title: DateUtil.java 
* @Package com.jinan.common 
* @Description: TODO(用一句话描述该文件做什么) 
* @author leixiaolin  
* @date 2014-7-19 上午10:44:55 
* @version V1.0   
*/
package com.common;

/** 
 * @ClassName: DateUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Administrator 
 * @date 2014-7-19 上午10:44:55 
 * @version 1.0 
 */
public class CheckUtil {
    public static void main(String[] args) {
    	System.out.println(checkUserPw(".*/_a2=!"));
        
    }
    /**
     * 判断是否是邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email)
    {// 验证邮箱的正则表达式 
     String format = "\\w{2,20}[@][a-z0-9]{2,}[.]\\p{Lower}{2,}";
     //p{Alpha}:内容是必选的，和字母字符[\p{Lower}\p{Upper}]等价。如：200896@163.com不是合法的。
     //w{2,15}: 2~15个[a-zA-Z_0-9]字符；w{}内容是必选的。 如：dyh@152.com是合法的。
     //[a-z0-9]{3,}：至少三个[a-z0-9]字符,[]内的是必选的；如：dyh200896@16.com是不合法的。
     //[.]:'.'号时必选的； 如：dyh200896@163com是不合法的。
     //p{Lower}{2,}小写字母，两个以上。如：dyh200896@163.c是不合法的。
     //\u4e00-\u9fa5判断是否是中文
     if (email.matches(format))
      { 
       return true;// 邮箱名合法，返回true 
      }
     else
      {
       return false;// 邮箱名不合法，返回false
      }
    } 
    /**
     * 验证用户名是否非法（是否是由数字，小写字母及下划线组成的2-20个字节字符串）
     * @param userName
     * @return
     */
    public static boolean checkUserName(String userName)
    {
    	String format = "\\w{2,20}";
    	if (userName.matches(format))
    	{ 
    		return true;// 用户名合法，返回true 
    	}
    	else
    	{
    		return false;// 用户名不合法，返回false
    	}
    } 
    /**
     * 验证密码是否非法（是否是由数字或小写字母或_或-.*=/!@组成的1-20字节字符串）
     * @param userPw
     * @return
     */
    public static boolean checkUserPw(String userPw)
    {
    	String format = "([a-zA-Z_0-9·]|[.*/=!@-]){1,20}";
    	if (userPw.matches(format))
    	{ 
    		return true;// 用户密码合法，返回true 
    	}
    	else
    	{
    		return false;// 用户密码不合法，返回false
    	}
    }
    /**
     * 验证昵称是否非法（是否是由数字或小写字母或下划线或中文或横杠组成的1-20个字节字符串）
     * @param nickName
     * @return
     */
    public static boolean checkNickName(String nickName)
    {
    	String format = "[\u4e00-\u9fa5a-zA-Z_0-9·]{1,20}";
    	if (nickName.matches(format))
    	{ 
    		return true;// 用户昵称合法，返回true 
    	}
    	else
    	{
    		return false;// 用户昵称不合法，返回false
    	}
    } 
}

