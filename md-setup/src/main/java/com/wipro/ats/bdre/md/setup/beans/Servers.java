/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wipro.ats.bdre.md.setup.beans;
// Generated Dec 21, 2015 11:26:14 PM by Hibernate Tools 3.2.2.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Servers generated by hbm2java
 */
@Entity
@Table(name="SERVERS"

)
public class Servers  implements java.io.Serializable {


     private Integer serverId;
     private String serverType;
     private String serverName;
     private String serverMetainfo;
     private String loginUser;
     private String loginPassword;
     private String sshPrivateKey;
     private String serverIp;

    public Servers() {
    }

	
    public Servers(String serverType, String serverName) {
        this.serverType = serverType;
        this.serverName = serverName;
    }
    public Servers(String serverType, String serverName, String serverMetainfo, String loginUser, String loginPassword, String sshPrivateKey, String serverIp) {
       this.serverType = serverType;
       this.serverName = serverName;
       this.serverMetainfo = serverMetainfo;
       this.loginUser = loginUser;
       this.loginPassword = loginPassword;
       this.sshPrivateKey = sshPrivateKey;
       this.serverIp = serverIp;
    }
   
     @Id 
    
    @Column(name="SERVER_ID", unique=true, nullable=false)
    public Integer getServerId() {
        return this.serverId;
    }
    
    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }
    
    @Column(name="SERVER_TYPE", nullable=false, length=45)
    public String getServerType() {
        return this.serverType;
    }
    
    public void setServerType(String serverType) {
        this.serverType = serverType;
    }
    
    @Column(name="SERVER_NAME", nullable=false, length=45)
    public String getServerName() {
        return this.serverName;
    }
    
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    
    @Column(name="SERVER_METAINFO", length=45)
    public String getServerMetainfo() {
        return this.serverMetainfo;
    }
    
    public void setServerMetainfo(String serverMetainfo) {
        this.serverMetainfo = serverMetainfo;
    }
    
    @Column(name="LOGIN_USER", length=45)
    public String getLoginUser() {
        return this.loginUser;
    }
    
    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }
    
    @Column(name="LOGIN_PASSWORD", length=45)
    public String getLoginPassword() {
        return this.loginPassword;
    }
    
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    
    @Column(name="SSH_PRIVATE_KEY", length=512)
    public String getSshPrivateKey() {
        return this.sshPrivateKey;
    }
    
    public void setSshPrivateKey(String sshPrivateKey) {
        this.sshPrivateKey = sshPrivateKey;
    }
    
    @Column(name="SERVER_IP", length=45)
    public String getServerIp() {
        return this.serverIp;
    }
    
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }




}


