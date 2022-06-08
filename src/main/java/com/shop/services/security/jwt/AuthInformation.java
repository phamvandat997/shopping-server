package com.shop.services.security.jwt;


import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthInformation {

    private final String id;
    private final String userName;
    private final String email;
    private final List<String> roles;

    AuthInformation(Builder builder){
        this.id = builder.id;
        this.userName = builder.userName;
        this.email = builder.email;
        this.roles = builder.roles;
    }
    
    @Getter
    @Setter
    public static class Builder {
        private String id;
        private String userName;
        private String email;
        private List<String> roles;

        public Builder(String id, String userName, String email, List<String> roles) {
            this.id = id;
            this.userName = userName;
            this.email = email;
            this.roles = roles;
        }

        public Builder() {

        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setRoles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public AuthInformation build() {
            return new AuthInformation(new Builder(id, userName, email, roles));
        }
    }
}