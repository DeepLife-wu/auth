/**
 * 
 */
package com.guoyao.auth.authorize.model.embedd;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wuchao
 * @Date 【2019年3月1日:下午2:43:18】
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Embeddable
public class GuoyaoUserConnectionId implements java.io.Serializable {
	
	@Column(name = "userid", nullable = false)
	private String userId;
	
	@Column(name = "providerid", nullable = false)
	private String providerId;
	
	@Column(name = "provideruserid", nullable = false)
	private String providerUserId;
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GuoyaoUserConnectionId))
			return false;
		GuoyaoUserConnectionId castOther = (GuoyaoUserConnectionId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())))
				&& ((this.getProviderId() == castOther.getProviderId()) || (this.getProviderId() != null
						&& castOther.getProviderId() != null && this.getProviderId().equals(castOther.getProviderId())))
				&& ((this.getProviderUserId() == castOther.getProviderUserId())
						|| (this.getProviderUserId() != null && castOther.getProviderUserId() != null
								&& this.getProviderUserId().equals(castOther.getProviderUserId())));
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result + (getProviderId() == null ? 0 : this.getProviderId().hashCode());
		result = 37 * result + (getProviderUserId() == null ? 0 : this.getProviderUserId().hashCode());
		return result;
	}
}
