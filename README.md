# interviewer
基于 springnboot3+langchain4j+RAG+chromadb+redis 的面试官智能体

## 🎯 项目简介
这是一个**AI 智能面试官系统**,基于 Spring Boot 3 + LangChain4j + RAG + ChromaDB + Redis 技术栈构建，能够与求职者进行实时对话面试，提供专业的问题评估和改进建议。

## 🏗️ 技术架构

### 后端技术栈
- **框架**: Spring Boot 3.5.0 + Java 17
- **AI 框架**: LangChain4j 1.0.1-beta6
- **大模型**: 硅基流动 API (Qwen2.5-72B-Instruct)
- **向量数据库**: ChromaDB
- **缓存**: Redis (存储会话记忆)
- **数据库**: MySQL + MyBatis

### 前端技术栈
- Vue 3 + Tailwind CSS
- 支持流式对话和打字机效果

## 🔧 核心功能

1. **模拟面试** - AI 面试官与求职者进行实时对话面试
2. **智能问答** - 根据候选人意向生成针对性面试题
3. **自动评估** - 对候选人回答给出专业反馈和改进建议
4. **面试记录** - 保存面试过程和评估结果到数据库
5. **流式响应** - 支持打字机效果的实时对话体验

## 📁 项目结构

```
src/
├── main/
│   ├── java/com/interviewai/consultant/
│   │   ├── aiservice/          # AI 服务接口
│   │   ├── config/             # 配置类 (AI 模型、向量库、RAG)
│   │   ├── controller/         # REST API 控制器
│   │   ├── mapper/             # MyBatis 数据访问层
│   │   ├── pojo/               # 数据模型
│   │   ├── repository/         # Redis 存储
│   │   ├── service/            # 业务逻辑层
│   │   ├── tools/              # AI 工具函数
│   │   └── InterviewerApplication.java  # 启动类
│   └── resources/
│       ├── static/index.html   # 前端聊天界面
│       ├── application.yml     # 配置文件
│       └── interviewer.txt     # AI 面试官提示词
└── test/                       # 测试代码
```

## ⚙️ 核心组件说明

| 组件 | 文件 | 说明 |
|------|------|------|
| AI 服务 | `InterviewService` | 定义大模型调用方式和 RAG 检索 |
| 控制器 | `InterviewController` | 提供 `/interview` 端点，处理流式请求 |
| 配置类 | `CommonConfig` | 初始化 AI 模型、向量库、RAG、记忆管理等 Bean |
| 工具类 | `InterviewTools` | 提供开始面试、查询记录、保存评估等功能 |
| 记忆存储 | `RedisChatMemoryStore` | 使用 Redis 存储对话历史 |
| 数据模型 | `InterviewRecord` | 面试记录实体 |
| 前端页面 | `index.html` | Vue 3 实现的聊天界面 |

## 🔍 核心功能实现

### 1. RAG 知识检索增强
- 从 `src/main/resources/content` 目录加载 PDF 文档
- 使用递归分割器进行文本切片（500 字符，100 重叠）
- 通过 Embedding 模型向量化后存入 ChromaDB
- 检索时相似度阈值 0.5，最多返回 3 条结果

### 2. 对话记忆管理
- 使用 Redis 存储多轮对话历史
- 每个会话保留最近 20 条消息
- 通过 `memoryId` 区分不同用户会话
- 数据过期时间 1 天

### 3. 函数调用能力
- `startInterview` - 记录候选人信息
- `findInterview` - 按手机号查询面试记录
- `saveEvaluation` - 保存面试评估和评分

### 4. 流式对话
- 使用 WebFlux 的 `Flux<String>` 实现 SSE 流式输出
- 前端实现打字机效果逐字显示
- 支持中断正在生成的回复

## 📊 数据模型

`InterviewRecord` 包含以下字段：
- **基本信息**: 姓名、性别、电话、面试时间
- **求职意向**: 岗位、工作年限、技术栈、期望薪资
- **评估结果**: 面试评估、综合评分 (0-100)

## 🌟 特色亮点

1. **接入国产大模型** - 使用硅基流动的 Qwen2.5-72B，成本低效果好
2. **RAG 增强** - 可加载面试知识库提升专业性
3. **持久化记忆** - Redis 存储对话历史，支持跨会话连续性
4. **流式交互** - 实时打字机效果，用户体验流畅
5. **功能完整** - 从面试开始到评估报告的全流程闭环

## 🚀 运行要求

- JDK 17+
- MySQL 数据库
- Redis 服务
- ChromaDB 向量数据库
- 硅基流动 API Key

## 📝 快速开始

1. 配置 `application.yml` 中的数据库连接和 API Key
2. 确保 Redis 和 ChromaDB 服务已启动
3. 运行 `InterviewerApplication.main()` 启动应用
4. 访问 `http://localhost:8080` 开始面试
