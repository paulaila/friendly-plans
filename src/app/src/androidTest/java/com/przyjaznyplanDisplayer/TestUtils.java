/*
 *
 *  * Copyright (c) 2016. Wydział Elektroniki, Telekomunikacji i Informatyki, Politechnika Gdańska
 *  * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or   (at your option) any later version.
 *  *
 *  * Copy of GNU General Public License is available at http://www.gnu.org/licenses/gpl-3.0.html
 *
 */

package com.przyjaznyplanDisplayer;

import com.przyjaznyplan.models.Activity;
import com.przyjaznyplan.models.Plan;
import com.przyjaznyplan.models.TypyWidokuAktywnosci;
import com.przyjaznyplan.models.TypyWidokuCzynnosci;
import com.przyjaznyplan.models.TypyWidokuPlanuAktywnosci;
import com.przyjaznyplan.models.User;
import com.przyjaznyplan.repositories.ActivityRepository;
import com.przyjaznyplan.repositories.PlanRepository;
import com.przyjaznyplan.repositories.UserRepository;
import com.przyjaznyplan.utils.BusinessLogic;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static final String ACTIVITY_BASE_NAME = "Aktywność";

    public static void setUpCurrentPlanWithActivities(int activitiesNumber) {
        List<Activity> activities = new ArrayList<>();
        for (int activityNumber = 0; activityNumber < activitiesNumber; activityNumber++)
            activities.add(new Activity(
                    ACTIVITY_BASE_NAME + activityNumber, Activity.TypeFlag.ACTIVITY
            ));

        ActivityRepository.insertActivitiesWithActions(activities);
        Plan currentPlan = PlanRepository.getPlanById(BusinessLogic.SYSTEM_CURRENT_PLAN_ID);
        PlanRepository.assignActivitiesToPlan(currentPlan, activities);
    }

    public static void setCurrentUserPreferences(
            TypyWidokuPlanuAktywnosci planViewType, TypyWidokuAktywnosci activityViewType, TypyWidokuCzynnosci actionViewType) {
        User currentUser = UserRepository.getCurrentUser();
        if (planViewType != null)
            UserRepository.setPlanViewTypeForUser(currentUser.getId(), planViewType);
        if (activityViewType != null)
            UserRepository.setActivityViewTypeForUser(currentUser.getId(), activityViewType);
        if (actionViewType != null)
            UserRepository.setActionViewTypeForUser(currentUser.getId(), actionViewType);
    }

}