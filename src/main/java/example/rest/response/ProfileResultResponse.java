/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.rest.response;

import example.thrift.Profile;
import example.thrift.TGetProfileResult;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class ProfileResultResponse {

    private long err;
    private List<ProfileResponse> profile;

    public long getErr() {
        return err;
    }

    public void setErr(long err) {
        this.err = err;
    }

    public List<ProfileResponse> getProfile() {
        return profile;
    }

    public void setProfile(List<ProfileResponse> profile) {
        this.profile = profile;
    }

    public static ProfileResultResponse parseFromTGetProfileResult(TGetProfileResult profile) {
        ProfileResultResponse res = new ProfileResultResponse();
        res.setErr(profile.getErr());
        List<ProfileResponse> profileResponses = new ArrayList<ProfileResponse>();
        if (profile.getProfile() != null) {
            for (Profile x : profile.getProfile()) {
                profileResponses.add(new ProfileResponse(x.getId(), x.getName(), x.getAge()));
            }
            res.setProfile(profileResponses);
        }
        return res;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (err ^ (err >>> 32));
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfileResultResponse other = (ProfileResultResponse) obj;
		if (err != other.err)
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		return true;
	}

}
