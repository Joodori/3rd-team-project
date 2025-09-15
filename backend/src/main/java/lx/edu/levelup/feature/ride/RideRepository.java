package lx.edu.levelup.feature.ride;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RideRepository {
	
	void insertRideBookConfirm(RideVO vo);
}
