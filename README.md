## Twitter News App
This is an app that utilizes tweets as search queries on google search. Then
the top five URLs are extracted for the user to view. This is meant to be a
form of an news app.


## TODOS:
	1. Needs to update dummy data to real data
		1.1 Get Twitter API to fetch JSON
		1.2 Set up new JSON data to print to screen
	2. Update the scroll feature to present a dropdown as a user is clicked
		2.1 This can be done with dummy data to get things going
		2.2 Data Binding offers the option to access xml views with code ex: textview android:title = "@{twitter_name}"
		(Update/Investigate 3/11: May open user search queries in a new intent when click on user instead
					  of using a drop down box(spinner))
	3. Fetch google search data to populate drop down menu items
		3.1 Use JSOUP 
		3.2 Extract URLs 
		3.3 Look into polishing presenting the data by also extracting any article titles url may have
		3.4 Look into supplying user with clickable links
			3.4.1 Stretch if we have time, there is a feature to open browser in app
	4. Polish app design up
		4.1 Animations
		4.2 Any extra features we can add for Project requirements

