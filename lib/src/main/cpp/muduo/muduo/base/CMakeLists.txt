set(base_SRCS
        AsyncLogging.cc
        Condition.cc
        CountDownLatch.cc
        CurrentThread.cc
        Date.cc
        Exception.cc
        FileUtil.cc
        LogFile.cc
        Logging.cc
        LogStream.cc
        ProcessInfo.cc
        Timestamp.cc
        Thread.cc
        ThreadPool.cc
        TimeZone.cc
)

add_library(muduo_base STATIC ${base_SRCS})
target_link_libraries(muduo_base)

