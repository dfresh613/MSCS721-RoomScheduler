# RoomScheduler
Simple console menu driven room scheduler framework.

This overly simple, intentially uncommented, and buggy room scheduler is intended to be used as an extendable framework for testing class assignments.

Changes made specifically in this fork:
Importing json will overwrite any existing room data
Exporting Json will export the rooms containing their meetings, and schedules
Date validator validates that the date specified by the user is in the year range 2016-2030, and validates that the months are 01-12
Time validator validates that the time specified by the user is in the 0 -23 hour, and 0-60 minutes.
There should be no exceptions thrown to the user, all should be handled
The Room scheduler shouldn't crash.
