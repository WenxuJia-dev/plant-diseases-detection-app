project(yolov5ncnn)

cmake_minimum_required(VERSION 3.4.1)

set(ncnn_DIR ${CMAKE_SOURCE_DIR}/${ANDROID_ABI}/lib/cmake/ncnn)
find_package(ncnn REQUIRED)

add_library(yolov5ncnn SHARED yolov5ncnn_jni.cpp)
target_link_libraries(yolov5ncnn
        ncnn
        jnigraphics
)
add_library(yolov5ncnndetector SHARED yolov5ncnndetector_jni.cpp)
target_link_libraries(yolov5ncnndetector
        ncnn
        jnigraphics
)
add_library(yolov5ncnnplant SHARED yolov5ncnnplant_jni.cpp)
target_link_libraries(yolov5ncnnplant
        ncnn
        jnigraphics
)