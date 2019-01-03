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
* @Title: DDConstants.java 
* @Package org.linys.common 
* @Description: TODO(用一句话描述该文件做什么) 
* @author leixiaolin  
* @date 2014-1-10 上午10:06:12 
* @version V1.0   
*/
package com.common;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/** 
 * @ClassName: DDConstants 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Administrator 
 * @date 2014-1-10 上午10:06:12 
 * @version 1.0 
 */
public class TokenUtil
{  
	    private static final String encryModel="MD5";  
	    /** 
	     * 32λmd5. 
	     * 32位小写md5加密 
	     * @param str 
	     * @return 
	     */  
	    public static String md5(String str) 
	    {  
	        return encrypt(encryModel, str);  
	    }  
	    public static String encrypt(String algorithm, String str) 
	    {  
	        try {  
	            MessageDigest md = MessageDigest.getInstance(algorithm);  
	            md.update(str.getBytes());  
	            StringBuffer sb = new StringBuffer();  
	            byte[] bytes = md.digest();  
	            for (int i = 0; i < bytes.length; i++) {  
	                int b = bytes[i] & 0xFF;  
	                if (b < 0x10) {  
	                    sb.append('0');  
	                }  
	                sb.append(Integer.toHexString(b));  
	            }  
	            return sb.toString();  
	        } catch (Exception e) {  
	            return "";  
	        }  
	    } 
	    /** 
	     *  
	     *  @Description    : 身份验证token值算法： 
	     *                              算法是：将特定的某几个参数一map的数据结构传入， 
	     *                              进行字典序排序以后进行md5加密,32位小写加密； 
	     *  @Method_Name    : authentication 
	     *  @param token        请求传过来的token 
	     *  @param srcData   约定用来计算token的参数 
	     *  @return  
	     */  
	    public static String authentication(Map<String , Object > srcData)
	    {   
	        List<Map.Entry<String,Object>> list = new ArrayList<Map.Entry<String,Object>>(srcData.entrySet());  
	        Collections.sort(list, new Comparator<Map.Entry<String, Object>>(){  
	            //升序排序  
	            public int compare(Entry<String,Object> o1, Entry<String,Object> o2){  
	                return o1.getKey().compareTo(o2.getKey());  
	            }  
	        });  
	          
	        StringBuffer srcSb = new StringBuffer();  
	        for(Map.Entry<String , Object>srcAtom : list){  
	            srcSb.append(String.valueOf(srcAtom.getValue()));  
	        }  
	        System.out.println("身份验证加密前字符串："+srcSb.toString());  
	        //计算token  
	        String token = md5(srcSb.toString());  
//	      System.out.println(cToken);//for test  
	        return token;  
	    }  
	} 
