package org.oopdev.xbalancer.common.converter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by kamilbukum on 31/03/2017.
 */

public class ConfigurationTestModel {
    @JsonProperty
    private String name;
    @JsonProperty
    private String surname;
    @JsonProperty
    private int age;

    @JsonSerialize
    private List<String> languages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("surname", surname)
                .append("age", age)
                .append("languages", languages)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        ConfigurationTestModel rhs = (ConfigurationTestModel) obj;
        return new EqualsBuilder()
                .append(this.name, rhs.name)
                .append(this.surname, rhs.surname)
                .append(this.age, rhs.age)
                .append(this.languages, rhs.languages)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .append(surname)
                .append(age)
                .append(languages)
                .toHashCode();
    }
}
