# Rhenium Client

## 客户端简介

> 铼，元素周期表第六周期第7族过渡金属元素。元素符号Re，原子序数75，相对原子质量186.2。

`Rhenium`是`Osmium`和`Iridium`的基本框架。

> 关系图：`Rhenium`->`Osmium`->`Iridium`

---

Rhenium Client 使用 Gradle 构建系统，已配置完整的运行 + 构建打包流程，开箱即用。<br>
内容方面，只进行最基本而必要的修改，如整合 Optifine，移除 Realms 与 Twitch 集成等。同时还修复了原版的一些小 Bug。

## 如何启动

1. 在 Gradle 同步后，您可以通过运行`runClient`任务以启动客户端。（需使用 JDK 17）

2. 若您正在使用非 Windows 平台，请自行导入对应平台下 Minecraft 1.8.9 with Optifine M6_pre2 的 Natives 库。

## 如何导出并游玩

1. 在 Gradle 同步后，运行`shadowJar`任务以导出客户端 Jar。

2. 接着，在你的启动器下载一个版本为 1.8.9 的原版游戏。（不需要额外安装 Optifine）

3. 用刚刚`shadowJar`任务导出的 Jar 替换掉原版游戏 Jar，注意文件名要和版本 Json 保持一致

4. 关闭启动器的游戏文件校验（防止客户端文件被更新掉），启动即可。 

> 注意要在启动器内选择 Java 版本为 Java 17。
