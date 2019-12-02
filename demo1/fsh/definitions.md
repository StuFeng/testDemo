
<a name="definitions"></a>
## 定义

<a name="link"></a>
### Link

|名称|说明|类型|
|---|---|---|
|**href**  <br>*可选*|**样例** : `"string"`|string|
|**templated**  <br>*可选*|**样例** : `true`|boolean|


<a name="a1a99db20c14165edbb7e38ee2c86a80"></a>
### Map«string,Link»
*类型* : < string, [Link](#link) > map


<a name="c4a077dec328835b76f33dc7575f8faa"></a>
### Page«ResourceVO»

|名称|说明|类型|
|---|---|---|
|**count**  <br>*可选*|数据总量  <br>**样例** : `0`|integer (int32)|
|**list**  <br>*可选*|数据内容  <br>**样例** : `[ "[resourcevo](#resourcevo)" ]`|< [ResourceVO](#resourcevo) > array|
|**page**  <br>*可选*|页数  <br>**样例** : `0`|integer (int32)|
|**pageSize**  <br>*可选*|单页数量  <br>**样例** : `0`|integer (int32)|


<a name="resourcevo"></a>
### ResourceVO

|名称|说明|类型|
|---|---|---|
|**app_id**  <br>*可选*|小程序id  <br>**样例** : `0`|integer (int64)|
|**audit_time**  <br>*可选*|审核时间  <br>**样例** : `"string"`|string|
|**create_time**  <br>*可选*|提交时间  <br>**样例** : `"string"`|string|
|**message**  <br>*可选*|审核信息  <br>**样例** : `"string"`|string|
|**rid**  <br>*可选*|物料id  <br>**样例** : `"string"`|string|
|**source**  <br>*可选*|物料来源  <br>**样例** : `"string"`|string|
|**status**  <br>*可选*|状态  <br>**样例** : `"string"`|string|
|**title**  <br>*可选*|标题  <br>**样例** : `"string"`|string|


<a name="result"></a>
### Result

|名称|说明|类型|
|---|---|---|
|**data**  <br>*可选*|数据  <br>**样例** : `"object"`|object|
|**errno**  <br>*可选*|业务状态  <br>**样例** : `0`|integer (int32)|
|**msg**  <br>*可选*|提示消息  <br>**样例** : `"string"`|string|


<a name="5d61053ecdba9536578180e771bcd9c9"></a>
### Result«List«企业城市»»

|名称|说明|类型|
|---|---|---|
|**data**  <br>*可选*|数据  <br>**样例** : `[ "[1e3b76b78e890831e6d2fd93a56e1f23](#1e3b76b78e890831e6d2fd93a56e1f23)" ]`|< [企业城市](#1e3b76b78e890831e6d2fd93a56e1f23) > array|
|**errno**  <br>*可选*|业务状态  <br>**样例** : `0`|integer (int32)|
|**msg**  <br>*可选*|提示消息  <br>**样例** : `"string"`|string|


<a name="c8f381acb36e2257d2d71f206fc682b0"></a>
### Result«List«行业领域»»

|名称|说明|类型|
|---|---|---|
|**data**  <br>*可选*|数据  <br>**样例** : `[ "[2dc847eabbbe799f6f2abd1316ff02ab](#2dc847eabbbe799f6f2abd1316ff02ab)" ]`|< [行业领域](#2dc847eabbbe799f6f2abd1316ff02ab) > array|
|**errno**  <br>*可选*|业务状态  <br>**样例** : `0`|integer (int32)|
|**msg**  <br>*可选*|提示消息  <br>**样例** : `"string"`|string|


<a name="2afd891baf08aad9f3037de86377cf52"></a>
### Result«Page«ResourceVO»»

|名称|说明|类型|
|---|---|---|
|**data**  <br>*可选*|数据  <br>**样例** : `"[c4a077dec328835b76f33dc7575f8faa](#c4a077dec328835b76f33dc7575f8faa)"`|[Page«ResourceVO»](#c4a077dec328835b76f33dc7575f8faa)|
|**errno**  <br>*可选*|业务状态  <br>**样例** : `0`|integer (int32)|
|**msg**  <br>*可选*|提示消息  <br>**样例** : `"string"`|string|


<a name="1e3b76b78e890831e6d2fd93a56e1f23"></a>
### 企业城市

|名称|说明|类型|
|---|---|---|
|**city**  <br>*可选*|市名称  <br>**样例** : `[ "string" ]`|< string > array|
|**province**  <br>*可选*|省名称  <br>**样例** : `"string"`|string|


<a name="2dc847eabbbe799f6f2abd1316ff02ab"></a>
### 行业领域

|名称|说明|类型|
|---|---|---|
|**domain**  <br>*可选*|行业领域名称  <br>**样例** : `"string"`|string|
|**id**  <br>*可选*|行业领域id  <br>**样例** : `0`|integer (int32)|



