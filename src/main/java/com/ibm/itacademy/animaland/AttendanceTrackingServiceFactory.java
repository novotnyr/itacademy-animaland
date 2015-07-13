package com.ibm.itacademy.animaland;

public class AttendanceTrackingServiceFactory {
	private static final AttendanceTrackingServiceFactory INSTANCE
		= new AttendanceTrackingServiceFactory();
	
	private AttendanceTrackingService attendanceTrackingService;
	
	private AttendanceTrackingServiceFactory() {
		// prevents second singleton instance
	}
	
	public static AttendanceTrackingServiceFactory newInstance() {
		return INSTANCE;
	}
	
	public AttendanceTrackingService getAttendanceTrackingService() {
		if (attendanceTrackingService == null) {
			attendanceTrackingService = new DbUtilsAttendanceTrackingService();
		}
		return attendanceTrackingService;
	}
	
	
	
	
	
	
	
	
	
	
}
