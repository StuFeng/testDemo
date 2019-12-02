
<a name="paths"></a>
## 资源

<a name="4611c741445f25eba68f7684a455fe7e"></a>
### Access管理
Access Controller


<a name="deleteresourceusingpost"></a>
#### 下线物料资源
```
POST /access/deleteresource
```


##### 说明
若有资源在小程序内下线或资源推送错误需要删除的情况，请调用该接口进行资源的删除，删除后的素材无法重新提交。


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**app_id**  <br>*可选*|app_id|integer (int64)|
|**Query**|**bd_app_id**  <br>*必填*|bd_app_id|integer (int64)|
|**Query**|**path**  <br>*必填*|智能小程序内页链接|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/access/deleteresource
```


###### 请求 query
```
json :
{
  "app_id" : 0,
  "bd_app_id" : 0,
  "path" : "/pages/detail/detail?id=100001"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="deletesitemapusingpost"></a>
#### 删除sitemap
```
POST /access/deletesitemap
```


##### 说明
若需要删除sitemap文件，请调用该接口，删除的仅为sitemap链接地址，对sitemap中已提交成功的素材无影响。


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**app_id**  <br>*可选*|app_id|integer (int64)|
|**Query**|**bd_app_id**  <br>*必填*|bd_app_id|integer (int64)|
|**Query**|**url**  <br>*必填*|sitemap链接|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/access/deletesitemap
```


###### 请求 query
```
json :
{
  "app_id" : 0,
  "bd_app_id" : 0,
  "url" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="deleteresourceusingpost_1"></a>
#### 下线物料资源
```
POST /access/resource/delete
```


##### 说明
若有资源在小程序内下线或资源推送错误需要删除的情况，请调用该接口进行资源的删除，删除后的素材无法重新提交。


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**app_id**  <br>*可选*|app_id|integer (int64)|
|**Query**|**bd_app_id**  <br>*必填*|bd_app_id|integer (int64)|
|**Query**|**path**  <br>*必填*|智能小程序内页链接|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/access/resource/delete
```


###### 请求 query
```
json :
{
  "app_id" : 0,
  "bd_app_id" : 0,
  "path" : "/pages/detail/detail?id=100001"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="queryresourceusingget"></a>
#### 查询物料资源
```
GET /access/resource/query
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**bd_app_id**  <br>*必填*|bd_app_id|integer (int64)|
|**Query**|**begin**  <br>*必填*|开始时间 (默认值前一天0点)|string|
|**Query**|**end**  <br>*必填*|结束时间 (默认值今天0点)|string|
|**Query**|**image_type**  <br>*可选*|image_type|integer (int32)|
|**Query**|**page_no**  <br>*必填*|结束时间 (默认值今天0点)|string|
|**Query**|**page_size**  <br>*必填*|结束时间 (默认值今天0点)|string|
|**Query**|**status**  <br>*必填*|状态（0: 全部 1: 审核中 2: 审核失败 3: 投放中 4: 已删除），默认值为0|string|
|**Query**|**title**  <br>*必填*|标题|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result«Page«ResourceVO»»](#2afd891baf08aad9f3037de86377cf52)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/access/resource/query
```


###### 请求 query
```
json :
{
  "bd_app_id" : 0,
  "begin" : "1569728735",
  "end" : "1569728735",
  "image_type" : 0,
  "page_no" : "页数(分页参数,第几页,默认值(1)",
  "page_size" : "单页展示数据量(分页参数,默认值(10)",
  "status" : "0",
  "title" : "百度智能小程序，给你全新的智能体验"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : {
    "count" : 0,
    "list" : [ {
      "app_id" : 0,
      "audit_time" : "string",
      "create_time" : "string",
      "message" : "string",
      "rid" : "string",
      "source" : "string",
      "status" : "string",
      "title" : "string"
    } ],
    "page" : 0,
    "pageSize" : 0
  },
  "errno" : 0,
  "msg" : "string"
}
```


<a name="submitresourceusingpost"></a>
#### 提交物料资源
```
POST /access/resource/submit
```


##### 说明
若有资源在小程序内需要提交或资源推送错误需要修改（或更新）的情况，请调用该接口进行资源的提交，该提交方式适用于少量的素材提交，每天有500次调用限制，如果素材量大建议使用sitemap的方式。


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**app_id**  <br>*可选*|app_id|integer (int64)|
|**Query**|**bd_app_id**  <br>*必填*|bd_app_id|integer (int64)|
|**Query**|**body**  <br>*必填*|消息体，物料的介绍|string|
|**Query**|**ext**  <br>*可选*|扩展信息（JSON格式，参考附录三）|string|
|**Query**|**feed_sub_type**  <br>*可选*|feed二级分类（参考附录二）|string|
|**Query**|**feed_type**  <br>*必填*|feed一级分类（参考附录二）|string|
|**Query**|**images**  <br>*必填*|封面图片链接（JSON格式）（最多3张，单图片最大2M） 建议尺寸：宽>=375 & 高>=250；建议比例 宽:高=1.5:1|string|
|**Query**|**mapp_sub_type**  <br>*必填*|资源子类型（参考附录一）|string|
|**Query**|**mapp_type**  <br>*必填*|资源类型（参考附录一）|string|
|**Query**|**path**  <br>*必填*|智能小程序落地页链接|string|
|**Query**|**tags**  <br>*可选*|资源标签，英文逗号分割，填写越准确详细可能带来更好的分发效果（最多10个，总长度最多100字）|string|
|**Query**|**title**  <br>*必填*|标题|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/access/resource/submit
```


###### 请求 query
```
json :
{
  "app_id" : 0,
  "bd_app_id" : 0,
  "body" : "以小程序粒度推送时，请填写小程序的详细介绍\n示例：爱说唱是一款基于百度语音技术的智能小程序。即便你对嘻哈音乐一窍不通，只需对它说上几句话，便可智能合成最酷的嘻哈音乐。同时还支持歌词查看和等功能，在线即可完成rap单曲的创作和分享。来吧，让我们在嘻哈的世界肆意妄为，一起Freestyle吧！\n以图文粒度推送时，请填写内容的正文\n示例：智能小程序，智能连接人与信息、人与服务、人与万物的开放生态，依托以百度APP为代表的全域流量，通过百度AI开放式赋能，精准连接用户，无需下载安装便可享受智慧超前的使用体验\n以视频粒度推送时，请填写视频的详细介绍\n示例：此片是当年为张国荣的参演而度身订造的电影，这也是他的歌唱和演艺事业的高峰时期。由包括人气歌手露云娜、偶像锺保罗等多位明星合演，片中以张国荣为首的角色最为丰富。",
  "ext" : "{\"publish_time\": \"2018年11月1日\"}",
  "feed_sub_type" : "明星八卦（可选有限集合）",
  "feed_type" : "娱乐（可选有限集合）",
  "images" : "[\"https://b.bdstatic.com/miniapp/resource/image/demo1.png\", \"https://b.bdstatic.com/miniapp/resource/image/demo2.png\"]",
  "mapp_sub_type" : "1001",
  "mapp_type" : "1000",
  "path" : "/pages/detail/detail?id=100001",
  "tags" : "示例：电影,吴亦凡",
  "title" : "百度智能小程序，给你全新的智能体验"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="updateresourceusingpost"></a>
#### 更新资源
```
POST /access/resource/update
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**app_id**  <br>*可选*|app_id|integer (int64)|
|**Query**|**bd_app_id**  <br>*必填*|bd_app_id|integer (int64)|
|**Query**|**body**  <br>*可选*|消息体，物料的介绍|string|
|**Query**|**ext**  <br>*可选*|扩展信息（JSON格式，参考附录三）|string|
|**Query**|**feed_sub_type**  <br>*可选*|feed二级分类（参考附录二）|string|
|**Query**|**feed_type**  <br>*可选*|feed一级分类（参考附录二）|string|
|**Query**|**images**  <br>*可选*|封面图片链接（JSON格式）（最多3张，单图片最大2M） 建议尺寸：宽>=375 & 高>=250；建议比例 宽:高=1.5:1|string|
|**Query**|**mapp_sub_type**  <br>*可选*|资源子类型（参考附录一）|string|
|**Query**|**mapp_type**  <br>*可选*|资源类型（参考附录一）|string|
|**Query**|**path**  <br>*必填*|智能小程序落地页链接|string|
|**Query**|**tags**  <br>*可选*|资源标签，英文逗号分割，填写越准确详细可能带来更好的分发效果（最多10个，总长度最多100字）|string|
|**Query**|**title**  <br>*可选*|标题|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/access/resource/update
```


###### 请求 query
```
json :
{
  "app_id" : 0,
  "bd_app_id" : 0,
  "body" : "以小程序粒度推送时，请填写小程序的详细介绍\n示例：爱说唱是一款基于百度语音技术的智能小程序。即便你对嘻哈音乐一窍不通，只需对它说上几句话，便可智能合成最酷的嘻哈音乐。同时还支持歌词查看和等功能，在线即可完成rap单曲的创作和分享。来吧，让我们在嘻哈的世界肆意妄为，一起Freestyle吧！\n以图文粒度推送时，请填写内容的正文\n示例：智能小程序，智能连接人与信息、人与服务、人与万物的开放生态，依托以百度APP为代表的全域流量，通过百度AI开放式赋能，精准连接用户，无需下载安装便可享受智慧超前的使用体验\n以视频粒度推送时，请填写视频的详细介绍\n示例：此片是当年为张国荣的参演而度身订造的电影，这也是他的歌唱和演艺事业的高峰时期。由包括人气歌手露云娜、偶像锺保罗等多位明星合演，片中以张国荣为首的角色最为丰富。",
  "ext" : "{\"publish_time\": \"2018年11月1日\"}",
  "feed_sub_type" : "明星八卦（可选有限集合）",
  "feed_type" : "娱乐（可选有限集合）",
  "images" : "[\"https://b.bdstatic.com/miniapp/resource/image/demo1.png\", \"https://b.bdstatic.com/miniapp/resource/image/demo2.png\"]",
  "mapp_sub_type" : "1001",
  "mapp_type" : "1000",
  "path" : "/pages/detail/detail?id=100001",
  "tags" : "示例：电影,吴亦凡",
  "title" : "百度智能小程序，给你全新的智能体验"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="deletesitemapusingpost_1"></a>
#### 删除sitemap
```
POST /access/sitemap/delete
```


##### 说明
若需要删除sitemap文件，请调用该接口，删除的仅为sitemap链接地址，对sitemap中已提交成功的素材无影响。


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**app_id**  <br>*可选*|app_id|integer (int64)|
|**Query**|**bd_app_id**  <br>*必填*|bd_app_id|integer (int64)|
|**Query**|**url**  <br>*必填*|sitemap链接|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/access/sitemap/delete
```


###### 请求 query
```
json :
{
  "app_id" : 0,
  "bd_app_id" : 0,
  "url" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="submitsitemapusingpost"></a>
#### 提交sitemap
```
POST /access/sitemap/submit
```


##### 说明
1.需要注意sitemap链接的内容格式（sitemap链接打开后，为多个loc链接；每个loc链接打开后，为单条素材内容。
2.提交素材或更新素材，则链接文件的type（即sitemap）选为“1”。
3.删除素材或下线素材，则链接文件的type选为“0”。
4.每个小程序，最多提交3条sitemap链接，建议一条type为1的sitemap链接，一条type为0的sitemap链接的（即增量/更新的sitemap一个，线下/删除的sitemap一个），若已满3条sitemap，若想添加新的sitemap链接，建议先删除一条sitemap，再进行添加新的sitemap。
5.提交sitemap链接方法共两种,两种提交方法任选其一即可：a.通过下方接口提交；b.通过智能小程序开发者平台端提交，提交入口：流量配置-信息流-上传素材-链接文件提交。


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**app_id**  <br>*可选*|app_id|integer (int64)|
|**Query**|**bd_app_id**  <br>*必填*|bd_app_id|integer (int64)|
|**Query**|**desc**  <br>*必填*|描述信息|string|
|**Query**|**frequency**  <br>*必填*|更新频率 3-每天 4-每周|string|
|**Query**|**type**  <br>*必填*|类型 1-增量/更新； 0-下线/删除|string|
|**Query**|**url**  <br>*必填*|sitemap链接|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/access/sitemap/submit
```


###### 请求 query
```
json :
{
  "app_id" : 0,
  "bd_app_id" : 0,
  "desc" : "智能小程序示例",
  "frequency" : "3",
  "type" : "1",
  "url" : "???"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="submitsitemapbyapiusingpost"></a>
#### 通过Api提交sitemap
```
POST /access/submit/sitemap
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**bd_app_id**  <br>*必填*|bd_app_id|integer (int64)|
|**Query**|**bd_tp_app_id**  <br>*可选*|bd_tp_app_id|integer (int64)|
|**Query**|**type**  <br>*必填*|上传级别 0：周级别；1：天级别|string|
|**Query**|**url_list**  <br>*必填*|url集合；上传级别上限，0：每天3000条，1：每天5000条 多个,分割|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/access/submit/sitemap
```


###### 请求 query
```
json :
{
  "bd_app_id" : 0,
  "bd_tp_app_id" : 0,
  "type" : "string",
  "url_list" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="submitresourceusingpost_1"></a>
#### 提交物料资源
```
POST /access/submitresource
```


##### 说明
若有资源在小程序内需要提交或资源推送错误需要修改（或更新）的情况，请调用该接口进行资源的提交，该提交方式适用于少量的素材提交，每天有500次调用限制，如果素材量大建议使用sitemap的方式。


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**app_id**  <br>*可选*|app_id|integer (int64)|
|**Query**|**bd_app_id**  <br>*必填*|bd_app_id|integer (int64)|
|**Query**|**body**  <br>*必填*|消息体，物料的介绍|string|
|**Query**|**ext**  <br>*可选*|扩展信息（JSON格式，参考附录三）|string|
|**Query**|**feed_sub_type**  <br>*可选*|feed二级分类（参考附录二）|string|
|**Query**|**feed_type**  <br>*必填*|feed一级分类（参考附录二）|string|
|**Query**|**images**  <br>*必填*|封面图片链接（JSON格式）（最多3张，单图片最大2M） 建议尺寸：宽>=375 & 高>=250；建议比例 宽:高=1.5:1|string|
|**Query**|**mapp_sub_type**  <br>*必填*|资源子类型（参考附录一）|string|
|**Query**|**mapp_type**  <br>*必填*|资源类型（参考附录一）|string|
|**Query**|**path**  <br>*必填*|智能小程序落地页链接|string|
|**Query**|**tags**  <br>*可选*|资源标签，英文逗号分割，填写越准确详细可能带来更好的分发效果（最多10个，总长度最多100字）|string|
|**Query**|**title**  <br>*必填*|标题|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/access/submitresource
```


###### 请求 query
```
json :
{
  "app_id" : 0,
  "bd_app_id" : 0,
  "body" : "以小程序粒度推送时，请填写小程序的详细介绍\n示例：爱说唱是一款基于百度语音技术的智能小程序。即便你对嘻哈音乐一窍不通，只需对它说上几句话，便可智能合成最酷的嘻哈音乐。同时还支持歌词查看和等功能，在线即可完成rap单曲的创作和分享。来吧，让我们在嘻哈的世界肆意妄为，一起Freestyle吧！\n以图文粒度推送时，请填写内容的正文\n示例：智能小程序，智能连接人与信息、人与服务、人与万物的开放生态，依托以百度APP为代表的全域流量，通过百度AI开放式赋能，精准连接用户，无需下载安装便可享受智慧超前的使用体验\n以视频粒度推送时，请填写视频的详细介绍\n示例：此片是当年为张国荣的参演而度身订造的电影，这也是他的歌唱和演艺事业的高峰时期。由包括人气歌手露云娜、偶像锺保罗等多位明星合演，片中以张国荣为首的角色最为丰富。",
  "ext" : "{\"publish_time\": \"2018年11月1日\"}",
  "feed_sub_type" : "明星八卦（可选有限集合）",
  "feed_type" : "娱乐（可选有限集合）",
  "images" : "[\"https://b.bdstatic.com/miniapp/resource/image/demo1.png\", \"https://b.bdstatic.com/miniapp/resource/image/demo2.png\"]",
  "mapp_sub_type" : "1001",
  "mapp_type" : "1000",
  "path" : "/pages/detail/detail?id=100001",
  "tags" : "示例：电影,吴亦凡",
  "title" : "百度智能小程序，给你全新的智能体验"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="submitsitemapusingpost_1"></a>
#### 提交sitemap
```
POST /access/submitsitemap
```


##### 说明
1.需要注意sitemap链接的内容格式（sitemap链接打开后，为多个loc链接；每个loc链接打开后，为单条素材内容。
2.提交素材或更新素材，则链接文件的type（即sitemap）选为“1”。
3.删除素材或下线素材，则链接文件的type选为“0”。
4.每个小程序，最多提交3条sitemap链接，建议一条type为1的sitemap链接，一条type为0的sitemap链接的（即增量/更新的sitemap一个，线下/删除的sitemap一个），若已满3条sitemap，若想添加新的sitemap链接，建议先删除一条sitemap，再进行添加新的sitemap。
5.提交sitemap链接方法共两种,两种提交方法任选其一即可：a.通过下方接口提交；b.通过智能小程序开发者平台端提交，提交入口：流量配置-信息流-上传素材-链接文件提交。


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**app_id**  <br>*可选*|app_id|integer (int64)|
|**Query**|**bd_app_id**  <br>*必填*|bd_app_id|integer (int64)|
|**Query**|**desc**  <br>*必填*|描述信息|string|
|**Query**|**frequency**  <br>*必填*|更新频率 3-每天 4-每周|string|
|**Query**|**type**  <br>*必填*|类型 1-增量/更新； 0-下线/删除|string|
|**Query**|**url**  <br>*必填*|sitemap链接|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/access/submitsitemap
```


###### 请求 query
```
json :
{
  "app_id" : 0,
  "bd_app_id" : 0,
  "desc" : "智能小程序示例",
  "frequency" : "3",
  "type" : "1",
  "url" : "???"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="submitsitemapbyapiusingpost_1"></a>
#### 通过Api提交sitemap
```
POST /access/submitsitemap/api
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**bd_app_id**  <br>*必填*|bd_app_id|integer (int64)|
|**Query**|**bd_tp_app_id**  <br>*可选*|bd_tp_app_id|integer (int64)|
|**Query**|**type**  <br>*必填*|上传级别 0：周级别；1：天级别|string|
|**Query**|**url_list**  <br>*必填*|url集合；上传级别上限，0：每天3000条，1：每天5000条 多个,分割|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/access/submitsitemap/api
```


###### 请求 query
```
json :
{
  "bd_app_id" : 0,
  "bd_tp_app_id" : 0,
  "type" : "string",
  "url_list" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="updateresourceusingpost_1"></a>
#### 更新资源
```
POST /access/updateresource
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**app_id**  <br>*可选*|app_id|integer (int64)|
|**Query**|**bd_app_id**  <br>*必填*|bd_app_id|integer (int64)|
|**Query**|**body**  <br>*可选*|消息体，物料的介绍|string|
|**Query**|**ext**  <br>*可选*|扩展信息（JSON格式，参考附录三）|string|
|**Query**|**feed_sub_type**  <br>*可选*|feed二级分类（参考附录二）|string|
|**Query**|**feed_type**  <br>*可选*|feed一级分类（参考附录二）|string|
|**Query**|**images**  <br>*可选*|封面图片链接（JSON格式）（最多3张，单图片最大2M） 建议尺寸：宽>=375 & 高>=250；建议比例 宽:高=1.5:1|string|
|**Query**|**mapp_sub_type**  <br>*可选*|资源子类型（参考附录一）|string|
|**Query**|**mapp_type**  <br>*可选*|资源类型（参考附录一）|string|
|**Query**|**path**  <br>*必填*|智能小程序落地页链接|string|
|**Query**|**tags**  <br>*可选*|资源标签，英文逗号分割，填写越准确详细可能带来更好的分发效果（最多10个，总长度最多100字）|string|
|**Query**|**title**  <br>*可选*|标题|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/access/updateresource
```


###### 请求 query
```
json :
{
  "app_id" : 0,
  "bd_app_id" : 0,
  "body" : "以小程序粒度推送时，请填写小程序的详细介绍\n示例：爱说唱是一款基于百度语音技术的智能小程序。即便你对嘻哈音乐一窍不通，只需对它说上几句话，便可智能合成最酷的嘻哈音乐。同时还支持歌词查看和等功能，在线即可完成rap单曲的创作和分享。来吧，让我们在嘻哈的世界肆意妄为，一起Freestyle吧！\n以图文粒度推送时，请填写内容的正文\n示例：智能小程序，智能连接人与信息、人与服务、人与万物的开放生态，依托以百度APP为代表的全域流量，通过百度AI开放式赋能，精准连接用户，无需下载安装便可享受智慧超前的使用体验\n以视频粒度推送时，请填写视频的详细介绍\n示例：此片是当年为张国荣的参演而度身订造的电影，这也是他的歌唱和演艺事业的高峰时期。由包括人气歌手露云娜、偶像锺保罗等多位明星合演，片中以张国荣为首的角色最为丰富。",
  "ext" : "{\"publish_time\": \"2018年11月1日\"}",
  "feed_sub_type" : "明星八卦（可选有限集合）",
  "feed_type" : "娱乐（可选有限集合）",
  "images" : "[\"https://b.bdstatic.com/miniapp/resource/image/demo1.png\", \"https://b.bdstatic.com/miniapp/resource/image/demo2.png\"]",
  "mapp_sub_type" : "1001",
  "mapp_type" : "1000",
  "path" : "/pages/detail/detail?id=100001",
  "tags" : "示例：电影,吴亦凡",
  "title" : "百度智能小程序，给你全新的智能体验"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="c4d78cd49b251c03ef779989d9e824fc"></a>
### Account管理
Account Controller


<a name="getcityusingget"></a>
#### getCity
```
GET /account/getcity
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**bd_app_id**  <br>*必填*|bd_app_id|integer (int64)|
|**Query**|**bdop_dev_user**  <br>*必填*|bdop_dev_user|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result«List«企业城市»»](#5d61053ecdba9536578180e771bcd9c9)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/account/getcity
```


###### 请求 query
```
json :
{
  "bd_app_id" : 0,
  "bdop_dev_user" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : [ {
    "city" : [ "string" ],
    "province" : "string"
  } ],
  "errno" : 0,
  "msg" : "string"
}
```


<a name="getdomainusingget"></a>
#### getDomain
```
GET /account/getdomain
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**bd_app_id**  <br>*必填*|bd_app_id|integer (int64)|
|**Query**|**bdop_dev_user**  <br>*必填*|bdop_dev_user|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result«List«行业领域»»](#c8f381acb36e2257d2d71f206fc682b0)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/account/getdomain
```


###### 请求 query
```
json :
{
  "bd_app_id" : 0,
  "bdop_dev_user" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : [ {
    "domain" : "string",
    "id" : 0
  } ],
  "errno" : 0,
  "msg" : "string"
}
```


<a name="document-controller_resource"></a>
### Document-controller
Document Controller


<a name="exportalldocumentusingpost"></a>
#### exportAllDocument
```
POST /document/export/all
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|无内容|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/document/export/all
```


<a name="operation-handler_resource"></a>
### Operation-handler
Operation Handler


<a name="handleusingget"></a>
#### handle
```
GET /actuator/auditevents
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/auditevents
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_1"></a>
#### handle
```
GET /actuator/beans
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/beans
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_3"></a>
#### handle
```
GET /actuator/caches
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/caches
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingdelete_1"></a>
#### handle
```
DELETE /actuator/caches
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/actuator/caches
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_2"></a>
#### handle
```
GET /actuator/caches/{cache}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/caches/{cache}
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingdelete"></a>
#### handle
```
DELETE /actuator/caches/{cache}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/caches/{cache}
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_4"></a>
#### handle
```
GET /actuator/conditions
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/conditions
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_5"></a>
#### handle
```
GET /actuator/configprops
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/configprops
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_7"></a>
#### handle
```
GET /actuator/env
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/env
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_6"></a>
#### handle
```
GET /actuator/env/{toMatch}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/env/{toMatch}
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_10"></a>
#### handle
```
GET /actuator/health
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/health
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_9"></a>
#### handle
```
GET /actuator/health/{component}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/health/{component}
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_8"></a>
#### handle
```
GET /actuator/health/{component}/{instance}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/health/{component}/{instance}
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_11"></a>
#### handle
```
GET /actuator/heapdump
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/octet-stream`


##### HTTP请求示例

###### 请求 path
```
/actuator/heapdump
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_12"></a>
#### handle
```
GET /actuator/httptrace
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/httptrace
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_13"></a>
#### handle
```
GET /actuator/info
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/info
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_15"></a>
#### handle
```
GET /actuator/loggers
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/loggers
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingpost"></a>
#### handle
```
POST /actuator/loggers/{name}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/actuator/loggers/{name}
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_14"></a>
#### handle
```
GET /actuator/loggers/{name}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/loggers/{name}
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_16"></a>
#### handle
```
GET /actuator/mappings
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/mappings
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_18"></a>
#### handle
```
GET /actuator/metrics
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/metrics
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_17"></a>
#### handle
```
GET /actuator/metrics/{requiredMetricName}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/metrics/{requiredMetricName}
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_19"></a>
#### handle
```
GET /actuator/prometheus
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `text/plain;version=0.0.4;charset=utf-8`


##### HTTP请求示例

###### 请求 path
```
/actuator/prometheus
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_20"></a>
#### handle
```
GET /actuator/scheduledtasks
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/scheduledtasks
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="handleusingget_21"></a>
#### handle
```
GET /actuator/threaddump
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**body**  <br>*可选*|body|< string, string > map|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|object|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator/threaddump
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```


<a name="tp-error-controller_resource"></a>
### Tp-error-controller
Tp Error Controller


<a name="handleerrorusingpost"></a>
#### handleError
```
POST /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="handleerrorusingget"></a>
#### handleError
```
GET /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="handleerrorusingput"></a>
#### handleError
```
PUT /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="handleerrorusingdelete"></a>
#### handleError
```
DELETE /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="handleerrorusingpatch"></a>
#### handleError
```
PATCH /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="handleerrorusinghead"></a>
#### handleError
```
HEAD /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="handleerrorusingoptions"></a>
#### handleError
```
OPTIONS /error
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/error
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "data" : "object",
  "errno" : 0,
  "msg" : "string"
}
```


<a name="web-mvc-links-handler_resource"></a>
### Web-mvc-links-handler
Web Mvc Links Handler


<a name="linksusingget"></a>
#### links
```
GET /actuator
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|< string, < string, [Link](#link) > map > map|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `application/json`
* `application/vnd.spring-boot.actuator.v2+json`


##### HTTP请求示例

###### 请求 path
```
/actuator
```


##### HTTP响应示例

###### 响应 200
```
json :
"object"
```



