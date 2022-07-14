HEALTH PLATFORM

We want to build a generic cloud based platform to record various health metric of a given user.
It has two main consumers.
1. User
2. Different Devices. 
   
Build an application that exposes following features to other applications
1. API to register user.
2. API to register a device to a user.
3. API to push metric. Sample metrics should be (height, weight, heart-rate, calorie,
   calorie-burn). The type of metrics supported are predefined and new type of metrics can be
   added.
4. API to get all data points of a metric for a user across different devices in a given duration.
5. API to get an appropriate aggregate (max, min, average etc) in a time range for a metric
   across different devices for a given user.
6. User Interface, to do the following
1. Ability for a user to login. Just username is enough. No need to worry about
   authentication.
2. Pull up user’s data in following cuts
1. Daily metric average for all metrics.
2. Weekly metric for all metrics.
3. Screen showing all devices that have been registered against the user.
