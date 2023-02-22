package com.sf.saas.bsp.rule.core.manager.config.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 01407460
 * @date 2022/9/7 9:54
 */
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private String nodes;

    private String master;

    private String password;

    private String type;

    private int database;

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }
}
