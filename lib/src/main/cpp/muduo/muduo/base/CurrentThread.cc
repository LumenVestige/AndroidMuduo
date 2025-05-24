// Use of this source code is governed by a BSD-style license
// that can be found in the License file.
//
// Author: Shuo Chen (chenshuo at chenshuo dot com)

#include "muduo/base/CurrentThread.h"

#include <cxxabi.h>
#include <stdlib.h>

namespace muduo {
    namespace CurrentThread {
        __thread int t_cachedTid = 0;
        __thread char t_tidString[32];
        __thread int t_tidStringLength = 6;
        __thread const char *t_threadName = "unknown";
        static_assert(std::is_same<int, pid_t>::value, "pid_t should be int");

    }  // namespace CurrentThread
}  // namespace muduo
