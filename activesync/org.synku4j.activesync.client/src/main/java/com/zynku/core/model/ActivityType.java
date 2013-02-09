package com.zynku.core.model;

/**
 * Sample types include:
 * - profile
 *   - user updated msisdn
 *   - user updated profile picture
 *   - user joined mportal
 *   - user left mportal
 * - contact
 *   - user imported contacts
 *   - user added contact
 *   - user webletised contact
 * - store
 *   - user purchased item
 * - weblet
 *   - user added weblet to web homepage
 *   - user added weblet to mobile device
 *   - user sent weblet to contact
 *   - user sent weblet to group
 *   - user accepted weblet request and added to web homepage
 *   - user rejected weblet request
 */
public class ActivityType {

	private Long activityTypeId;
	private ActivityCategory activityCategory;
		
}
