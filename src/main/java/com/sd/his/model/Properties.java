package com.sd.his.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Properties")
public class Properties implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "CLIENT_ID")
    private String clientId;

    @Column(name = "CLIENT_SECRET")
    private String clientSecret;

    @Column(name = "AUTH_SERVER_SCHEME")
    private String authServerScheme;

    public Properties(String clientId, String clientSecret, String authServerScheme) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authServerScheme = authServerScheme;
    }

    public Properties() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAuthServerScheme() {
        return authServerScheme;
    }

    public void setAuthServerScheme(String authServerScheme) {
        this.authServerScheme = authServerScheme;
    }
}