Change Log
==========

Version 1.0.8 *(2015-01-23)*
--------------------------------

* Add default modules for activity and application scope for easier use of standard android services

Version 1.0.7 *(2014-11-15)*
--------------------------------

* Fixed duplicate injections due to onActivityCreated being called multiple times

Version 1.0.6 *(2014-06-01)*
--------------------------------

* Add StabbedIntentService 
* Change StabbedService to implement StabbedContext

Version 1.0.5 *(2013-11-19)*
--------------------------------

* Fixed scope of slf4j-simple which should only be used in tests

Version 1.0.4 *(2013-11-17)*
--------------------------------

* Added stabbed `DialogFragment`s
* Added stabbed `ListFragment`s
* Added stabbed `PreferenceFragment`

Version 1.0.3 *(2013-11-16)*
----------------------------

* Replaced exception with logging a warning when using inject before the graph has been created
* Added extensive test coverage

Version 1.0.2 *(2013-10-30)*
----------------------------

* Added `@Nullable` annotations where applicable
* Added `@ForActivity` and `@ForApplication` qualifier annotations
* Added StabbedSherlockPreferenceActivity
* Added StabbedService
* Added StabbedBroadcastReceiver

Version 1.0.1 *(2013-10-20)*
----------------------------

* Fix wrong creation of activity graph

Version 1.0.0 *(2013-10-20)* *DO NOT USE*
-----------------------------------------

* Initial release.
