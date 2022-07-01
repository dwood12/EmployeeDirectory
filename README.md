## Build tools & versions used
- Build tools: 30.0.3

- Gradle: 7.3.3

- Compile/Target SDK: 32

- Min SDK: 23

## Steps to run the app
Perform a clean build, then run APK on device.

By default, the employee directory is loaded. The menu can toggle between the employee directory, empty employee directory, and the malformed employee directory. Pull to refresh will load the default employee directory again.

## What areas of the app did you focus on?

I spent time planning and strategizing the app architecture to ensure each level of code was contained, careful not to give too much responsibility to the UI and only expose what was necessary. Though the task of pulling a list of JSON data and presenting it to the front end is fairly trivial, and architecting this much may be overkill, it makes adding more features easier in the future.

[UI] <-> [ViewModel] <- [UseCase] <- [Repository] <- [API/Database]

## What was the reason for your focus? What problems were you trying to solve?

In Android development, architecture has been evolving to make developers lives easier, even if it requires a bit more planning. Rather than having too much happening in the UI level (Activity, Fragment), separating concerns helps manage and isolate issues in the app for easier debugging. With older apps, this technical debt is a bit more challenging to manage due to deadlines, requirements, and codebase size. With a new app, it is easier to ensure its done correctly from the beginning with careful planning. Using MVVM will help, and Google has recently [suggested](https://developer.android.com/topic/architecture) a general way of organizing the app code. I also used dependency injection to help manage dependencies.

## How long did you spend on this project?
4.5 hours, with some extra time spent reviewing code for quality.

## Did you make any trade-offs for this project? What would you have done differently with more time?

I spent time persisting the data to disk with Room before realizing that was not a requirement (only images needed to be cached). This could have cut some time spent setting that up and helped polish some of the UI.
Using Compose would have made the UI much quicker to set up rather than using much of the boiler plate that comes with using `RecyclerView` (Adapter, ViewHolder), but I have not had enough experience to use that for this project. If I had more time. I would have also been more specific about notifying the user of various errors that occur in the app, rather than just catching any error and not loading the employee directory. For instance, notifying the user of no network connection vs not being able to connect to the endpoint.

## What do you think is the weakest part of your project?

The UI works, but is not particularly interesting or creative. The `GetEmployeeUseCase` takes an enum as an argument to determine which endpoint to obtain the data from. As an alternative, it might have been better to use multiple use cases instead of making the viewmodel pass the enum as a parameter. As mentioned before, the pull to refresh doesn't remember the last selected menu option and will just grab the employee directory data (instead of malformed or empty). This is fixable with persisting the user preference, but didn't seem necessary for the scope of this problem.

## Did you copy any code or dependencies? Please make sure to attribute them here!
[`MainCoroutineRule`](https://github.com/google/iosched/blob/main/test-shared/src/main/java/com/google/samples/apps/iosched/test/data/MainCoroutineRule.kt) - Useful for unit testing with coroutines

[`UseCase`](https://github.com/google/iosched/blob/main/shared/src/main/java/com/google/samples/apps/iosched/shared/domain/CoroutineUseCase.kt) - Useful for generalizing testable use cases with various input and output that handles errors and suspending calls

[Picasso](https://square.github.io/picasso/) - Image downloading and caching

[Timber](https://github.com/JakeWharton/timber) - Logging utility

[Retrofit](https://square.github.io/retrofit/) - HTTP Request Client

[Kodein](https://github.com/kosi-libs/Kodein) - DI

## Is there any other information you’d like us to know?

I enjoyed this exercise of setting up a small functional app, as it put some of my Android development abilities to the test and made me think about the best way to organize, retrieve, and display the data, as well as handle edge cases like network and device rotation.
