# Paper [![Paper 构建状态](https://img.shields.io/github/actions/workflow/status/PaperMC/Paper/build.yml?branch=main)](https://github.com/PaperMC/Paper/actions)
[![Discord](https://img.shields.io/discord/289587909051416579.svg?label=&logo=discord&logoColor=ffffff&color=7389D8&labelColor=6A7EC2)](https://discord.gg/papermc)
[![GitHub Sponsors](https://img.shields.io/github/sponsors/papermc?label=GitHub%20Sponsors)](https://github.com/sponsors/PaperMC)
[![Open Collective](https://img.shields.io/opencollective/all/papermc?label=OpenCollective%20Sponsors)](https://opencollective.com/papermc)

Paper 是目前使用最广泛、性能卓越的 Minecraft 服务端，致力于修复游戏玩法与游戏机制中的不一致问题。

**支持与项目讨论：**

- [官方论坛](https://forums.papermc.io/) 或 [Discord](https://discord.gg/papermc)

如何使用（服务器管理员）
----------------------

Paperclip 是一个 JAR 文件，你可以像运行普通 JAR 文件一样下载并运行它。

请从我们的[下载页面](https://papermc.io/downloads/paper)下载 Paper。

直接在服务器上运行 Paperclip JAR 文件即可，和过去一样简单。

- Paper 使用文档：[docs.papermc.io](https://docs.papermc.io)
- 想提前了解即将推出的功能，请[查看这里](https://github.com/PaperMC/Paper/projects)

如何使用（插件开发者）
----------------------

- 请查看我们的 [API](paper-api)
- 请查看即将推出、等待处理以及近期新增的 [API](https://github.com/orgs/PaperMC/projects/2/views/4)
- Paper API Javadocs：[papermc.io/javadocs](https://papermc.io/javadocs/)

#### `paper-api` 仓库配置

##### Maven

```xml
<repository>
    <id>papermc</id>
    <url>https://repo.papermc.io/repository/maven-public/</url>
</repository>
```

```xml
<dependency>
    <groupId>io.papermc.paper</groupId>
    <artifactId>paper-api</artifactId>
    <version>1.21.11-R0.1-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
```

##### Gradle

```kotlin
repositories {
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}
```

如何使用（从源代码编译 JAR）
----------------------------

要编译 Paper，你需要安装 JDK 21，并确保网络连接正常。

克隆此仓库后，在终端中运行：

```bash
./gradlew applyPatches
```

然后运行：

```bash
./gradlew createMojmapBundlerJar
```

编译生成的 JAR 文件位于：

```text
paper-server/build/libs
```

如需查看完整的任务列表，请运行：

```bash
./gradlew tasks
```

如何提交拉取请求（Pull Request）
--------------------------------

请参阅[贡献指南](CONTRIBUTING.md)。

旧版本（1.21.3 及更早版本）
----------------------------

对于 1.8 至 1.21.3 版本的分支，请查看我们的[归档仓库](https://github.com/PaperMC/Paper-archive)。

支持我们
--------

首先，感谢你考虑为我们提供帮助，我们对此深表感谢！

PaperMC 每年需要承担多项持续性支出，其中大部分与基础设施有关。Paper 通过 [Open Source Collective 财政托管机构](https://opencollective.com/opensource)使用 [Open Collective](https://opencollective.com/) 管理相关支出。Open Collective 让我们的财务状况高度透明，因此你可以随时查看捐款的使用情况。你可以在我们的[网站](https://papermc.io/sponsors)上进一步了解如何从财务方面支持 PaperMC。

你可以通过[这里](https://opencollective.com/papermc)访问我们的集体资金页面，也可以通过 [GitHub Sponsors](https://github.com/sponsors/PaperMC) 捐款，相关资金同样会用于该集体资金项目。

特别鸣谢
--------

[![YourKit 标志](https://www.yourkit.com/images/yklogo.png)](https://www.yourkit.com/)

[YourKit](https://www.yourkit.com/) 是优秀 Java 分析器的开发商，为各类开源项目提供功能完整的 [Java](https://www.yourkit.com/java/profiler) 和 [.NET](https://www.yourkit.com/.net/profiler) 应用程序分析器。感谢 YourKit 授予 Paper 开源软件许可证，使我们能够持续改进软件品质。

[<img src="https://user-images.githubusercontent.com/21148213/121807008-8ffc6700-cc52-11eb-96a7-2f6f260f8fda.png" alt="" width="150">](https://www.jetbrains.com)

[JetBrains](https://www.jetbrains.com/) 是 IntelliJ IDEA 的开发商，并通过其[开源许可证计划](https://www.jetbrains.com/opensource/)为 Paper 提供支持。IntelliJ IDEA 是开发 Paper 时推荐使用的集成开发环境（IDE），Paper 团队的大多数成员也在使用它。

感谢所有赞助商！

[![赞助商图片](https://raw.githubusercontent.com/PaperMC/papermc.io/data/sponsors.png)](https://papermc.io/sponsors)
