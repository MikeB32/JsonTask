#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_kspe_jsontask_activities_ListActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "https://randomuser.me/api/?page=1&results=10&seed=abc";
    return env->NewStringUTF(hello.c_str());
}
