package com.inva.hipstertest.service.dto;


import com.inva.hipstertest.domain.Parent;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Pupil entity.
 */
public class PupilDTO implements Serializable {

    private Long id;

    @NotNull
    private Boolean enabled;

    private Long userId;

    private Long formId;

    private Set<Parent> parentsId;

    private String pupilFirstName;

    public String getPupilFirstName() {
        return pupilFirstName;
    }

    public void setPupilFirstName(String pupilFirstName) {
        this.pupilFirstName = pupilFirstName;
    }

    private String pupilLastName;

    public String getPupilLastName() {
        return pupilLastName;
    }

    public void setPupilLastName(String pupilLastName) {
        this.pupilLastName = pupilLastName;
    }

    public Set<Parent> getParentsId() {
        return parentsId;
    }

    public void setParentsId(Set<Parent> parentsId) {
        this.parentsId = parentsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PupilDTO pupilDTO = (PupilDTO) o;

        if ( ! Objects.equals(id, pupilDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PupilDTO{" +
            "id=" + id +
            ", enabled='" + enabled + "'" +
            '}';
    }
}
