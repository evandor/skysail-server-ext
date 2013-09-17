/**
 * Copyright 2011 Carsten Gräf
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 */
package de.twenty11.skysail.server.um.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import de.twenty11.skysail.common.forms.Field;

/**
 * copied here as of http://www.eclipse.org/forums/index.php/t/457852/
 * 
 */
@Entity
@Table(name = "um_users")
@NamedQuery(name = "findByName", query = "SELECT c FROM SkysailUser c WHERE c.username = :username")
public class SkysailUser1 implements Serializable {

    private static final long serialVersionUID = -3030387756527785881L;

    @Id
    @GeneratedValue
    private int id;

    @Field
    @Size(min = 3, message = "username must have at least three characters")
    private String username;

    @Field
    private String password;

    private List<SkysailRole> roles = new ArrayList<SkysailRole>();

    public SkysailUser1() {
        roles.add(new SkysailRole("test"));
    }

    public SkysailUser1(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int userId) {
        this.id = userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<SkysailRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SkysailRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return username + "[" + roles.toString() + "]";
    }
}