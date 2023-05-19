
# NYTimes


# App developed in MVC pattern with Data binding with JDK version 11 , Android Studio version chipmunk

# Ext Libraries Used - 
                   1 Retrofit,Gson,Gson-converter  (API calling)
                   2 Glide   (to load image from link)
                   3 jacoco (for coverage report)

# Steps to generate coverage report
                   1 Clean and build the app 
                   2  Run the below command for coverage report
                     ./gradlew testDebugUnitTest 
                     this would generate report in "build/reports/tests"
                   3 check the coverage report run the below command
                     ./gradlew createDebugCoverageReport
                    This will generate reports under "build/reports/coverage" 

                