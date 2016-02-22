package com.ratedforum.user.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractAuditEntity implements Serializable {

	private static final long serialVersionUID = 6384069660089559035L;
	
	@Transient
	private SecurityContext securityContext = SecurityContextHolder.getContext();
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_ts", nullable = false)
	private Date createdTs;
        
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_ts", nullable = false)
    private Date updatedTs;
    
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;
	
    @Version
    @Column(name = "version", nullable = false)
    private Long version;
    
    @PrePersist
    protected void onCreate() {
    	updatedTs = createdTs = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
    	updatedTs = new Date();
    	if(securityContext.getAuthentication() != null){
    		String updateByUserId = securityContext.getAuthentication().getName();
    		updatedBy = updateByUserId;
    	}	
    }

	public Long getVersion() {
		return version;
	}
	
	public void setVersion(Long version) {
		this.version = version;
	}
	
	public Date getCreatedTs() {
		return createdTs;
	}
	
	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}
	
	public Date getUpdatedTs() {
		return updatedTs;
	}
	
	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}
	
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result + ((updatedTs == null) ? 0 : updatedTs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		AbstractAuditEntity other = (AbstractAuditEntity) obj;
		if (createdTs == null) {
			if (other.createdTs != null) return false;
		} else if (!createdTs.equals(other.createdTs)) return false;
		if (updatedBy == null) {
			if (other.updatedBy != null) return false;
		} else if (!updatedBy.equals(other.updatedBy)) return false;
		if (updatedTs == null) {
			if (other.updatedTs != null) return false;
		} else if (!updatedTs.equals(other.updatedTs)) return false;
		return true;
	}
}
