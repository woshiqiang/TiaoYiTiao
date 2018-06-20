cmake_minimum_required(VERSION 3.4.1)

find_library( log-lib
              log )

# set(distribution_DIR ../../../../libs)

add_library( native-lib
             SHARED
             src/main/cpp/native-lib.cpp )

add_library( opencv_java3
             SHARED
             IMPORTED)
set_target_properties( opencv_java3
                       PROPERTIES IMPORTED_LOCATION
                       # ${distribution_DIR}/armeabi/libopencv_java3.so
                       ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libopencv_java3.so
                       )
add_library(lib_opencv SHARED IMPORTED)
set_target_properties(lib_opencv PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/libs/${ANDROID_ABI}/libopencv_java3.so)



include_directories(src/main/cpp/include/)

target_link_libraries( native-lib
                       opencv_java3
                       ${log-lib} )

