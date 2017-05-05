# AppDevTemplate
Android APP develop template.

Android 快速开发模版。

# 食用方法：
- 方法一：
    1. 将此项目作为一个库依赖到新建的项目中。
    2. 详细依赖方法及注意点可以看 [这里](http://blog.csdn.net/zgh0711/article/details/70948532)
    3. 在项目根目录的 build.gradle 文件中添加 maven 库支持，因为引入了一个 recyclerView 的库


        ```
        allprojects {
             repositories {
                 jcenter()
                 maven { url "https://jitpack.io" }
             }
         }
         ```

    4. 将应用的 minSdkVersion 设为 19 及以上，不然会和另一个设置状态栏颜色的库相冲突。


- 方法二：

    1. 用 Android Studio 将此项目 Checkout 到本地并成功编译一次。
    2. 将包名改为要开发的项目的包名。
    3. 用 Android Studio 新建一个项目，包名为要开发的项目的包名。
    4. 将改过包名后的模版项目中的以下文件复制到上面新建的项目中并覆盖同名文件。
       - app 文件夹
       - build.gradle 文件
       - .gitignore 文件
    5. 编译运行，正常后即可开始开发新项目。