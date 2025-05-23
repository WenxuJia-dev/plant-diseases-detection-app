package com.tencent.yolov5ncnn;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Adapters.PlantdocAdapter;
import Adapters.WheatAdapter;
import domain.AppleDomain;
import domain.WheatDomain;

public class detect_plant extends Activity {

    private static final int SELECT_IMAGE = 1;
    private ImageView imageView,imageView2;
    private Bitmap bitmap = null;
    private Bitmap yourSelectedImage = null;
    private Yolov5Ncnnplant yolov5ncnn=new Yolov5Ncnnplant();

    private RecyclerView.Adapter adapter;
    private RecyclerView review;
    public void initRecycleView(){
        ArrayList<AppleDomain> plantlist=new ArrayList<>();
        plantlist.add(new AppleDomain("Corn","大斑病",
                "玉米大斑病是由大斑病凸脐蠕孢引起的、发生在玉米的病害，是玉米的重要病害之一，广泛分布于世界各玉米栽培地区。",
                "大面积种植感病品种。\n" +
                        "田间存在大量病原菌，且病菌可以在田间残留的病株上以菌丝体和分生孢子两种形态越冬，成为第二年发病的初侵染源。\n" +
                        "具有适宜发病的环境条件，如中温高湿的气候条件，20~25℃、相对湿度90%以上有利于病害发展。",
                "主要危害叶片，严重时也危害叶鞘和苞叶。\n" +
                        "病斑一般先从底部叶片开始发生，逐步向上扩展，严重时能遍及全株，但也有从中上部叶片发病的情况。\n" +
                        "病斑呈长梭形，灰褐色或黄褐色，长5~10厘米，宽1厘米左右，有的病斑更大，或几个病斑连接成大型不规则形枯斑",
                "选用抗病品种，并要注意品种的合理搭配与轮换，避免品种单一化。\n" +
                        "实行轮作倒茬，避免重茬、迎茬种植。\n" +
                        "改善田间通风、透光条件，促进玉米健壮生长。\n" +
                        "摘除植株基部黄叶、病叶，减少再次侵染菌源。\n" +
                        "喷施杀菌剂，如80%代森锰锌可湿性粉剂、70%甲基托布津可湿性粉剂、50%多菌灵可湿性粉剂、75%百菌清可湿性粉剂等。",
                "corn_leaf_blight"));
        plantlist.add(new AppleDomain("Potato","晚疫病",
                "马铃薯晚疫病是一种由致病疫霉（Phytophthora infestans）引起的真菌性病害，对马铃薯作物具有极大的经济影响",
                "病原物：马铃薯晚疫病的病原为致病疫霉菌，该病菌的菌丝无色、无隔膜，在寄主细胞间隙生长，以纽扣状吸胞伸入寄主细胞中吸取养分。\n" +
                        "环境条件：病菌的生长和繁殖需要适宜的温度和湿度条件。菌丝生长的最适温度为20~23℃，孢子囊形成的最适温度为19~22℃。在相对湿度达到95%~97%时，孢子囊才能大量形成。因此，晚疫病多在阴雨潮湿、气温偏低的地区与年份发生。",
                "马铃薯晚疫病可发生在叶片、茎和薯块上，其症状表现如下：\n" +
                        "\n" +
                        "叶片：叶片染病时，大多数先从叶尖和叶缘开始，产生绿褐色的水渍状斑点，斑点周围常有一圈浅绿色的晕圈。在潮湿的情况下，病斑迅速扩大变为褐色，并产生一圈白色霉状物（孢囊梗和孢子囊），尤其在叶背面特别显著。严重时叶片萎垂、发黑，可造成全株枯死。\n" +
                        "茎部：茎部受害时，会出现长短不一的褐色条斑。在天气潮湿时，表面也会长出白霉，但较为稀疏。\n" +
                        "薯块：薯块受害时，初生褐色或稍带紫色的病斑，以后稍凹陷，病斑可扩大。切开病部，可见皮下薯肉呈褐色，且逐渐向四周及内部发展。在高温下培养2~3天后，亦可长出白色霉状物。",
                "农业防治：\n" +
                        "选用抗病品种：种植抗病品种是防控马铃薯晚疫病最经济、有效的办法。如青薯9号、克新、陇薯等品种均具有较好的抗病性。\n" +
                        "实行轮作：避免与茄科作物连作或临近种植，应与十字花科蔬菜实行3年以上轮作。\n" +
                        "加强田间管理：施足基肥，实行配方施肥，避免偏施氮肥，增施磷、钾肥。及时防除杂草，合理整枝、摘心、打杈，减少养分消耗，促进主茎的生长。合理密植，改善田间通风透光条件，降低田间湿度，减轻病害的发生。\n" +
                        "化学防治：\n" +
                        "在病害发生初期，可选用代森锰锌、甲霜灵锰锌、霜脲锰锌等药剂进行喷雾防治。每隔710天喷一次，连续喷23次。\n" +
                        "在病害流行年份或地区，可根据病情发展情况适当增加喷药次数和用药量。但需注意药剂的交替使用，避免产生抗药性。\n" +
                        "生物防治：\n" +
                        "利用生物制剂进行防治，如木霉菌、芽孢杆菌等生物菌剂对马铃薯晚疫病具有一定的防治效果。\n" +
                        "通过改善土壤微生态环境，增加土壤中有益微生物的数量和种类，提高马铃薯植株的抗病性。",
                "potato_leaf_late_blight"
        ));
        plantlist.add(new AppleDomain("Corn","锈病",
                "玉米锈病是一种真菌性病害，主要侵染玉米的叶片，也能够侵染叶鞘、茎秆和苞叶，对玉米的产量和质量造成严重影响。该病在全球各玉米栽培区均有发生，在中国则广泛分布于南北主要玉米产区，尤其在北方夏玉米种植区以及华东、华南、西南等南方各省较为常见。",
                "病原菌：玉米锈病的主要病原菌为玉米柄锈菌，其夏孢子在适宜的环境条件下能够萌发并侵入玉米植株，导致病害的发生。\n" +
                        "环境因素：温暖潮湿的环境有利于玉米锈病的发生。发病温度范围为1535℃，最适发病环境温度为2030℃，相对湿度95%以上。此外，连作地、排水不良的田块以及种植密度过高、通风透光差的田块也更容易发病。",
                "玉米锈病的症状主要表现在叶片上，也可侵染叶鞘、茎秆和苞叶。发病初期，叶片两面出现淡黄白色小斑，四周有黄色晕圈，后突起形成黄褐色乃至红褐色疱斑，散生或聚生圆形或长圆形，即病菌的夏孢子堆。孢子堆表皮破裂后，散出铁锈状夏孢子。后期病斑或其附近又出现黑色疱斑，即病菌的冬孢子堆，长椭圆形，疱斑破裂散出黑褐色粉状物。发病严重时，整张叶片可布满锈褐色病斑，引起叶片枯黄，同时可危害苞叶、果穗和雄花。",
                "农业防治：\n" +
                        "选育耐病品种，种植中、晚熟品种，以降低病害的发生几率。\n" +
                        "实行茬口轮作，避免连作，减少病原菌的积累。\n" +
                        "加强田间管理，合理施肥，避免偏施氮肥，提高植株的抗病性。\n" +
                        "清洁田园，收获后及时清除田间病残体，带出地外集中烧毁或深埋，深翻土壤，减少土表越冬病菌。\n" +
                        "化学防治：\n" +
                        "在发病初期开始喷药，每隔710天喷1次，连续喷12次。\n" +
                        "可选用20%苯醚甲环唑微乳剂、30%氟硅唑微乳剂、40%氟硅唑乳油、18%戊唑醇微乳剂、10%苯醚甲环唑水分散粒剂、43%戊唑醇悬浮剂、75%肟菌·戊唑醇水分散剂等喷雾防治。\n" +
                        "也可使用80%代森锰锌可湿性粉剂等喷雾防治。",
                "corn_rust_leaf"));
        plantlist.add(new AppleDomain("Tomato","晚疫病",
                "番茄晚疫病是一种世界性病害，在中国各地均有发生。该病可危害番茄的幼苗、茎、叶及果实，对番茄的产量和品质造成严重影响。致病疫霉为疫霉科疫霉属卵菌，其寄主范围相对较窄，主要侵染茄科植物，如马铃薯、番茄、茄子等。",
                "番茄晚疫病的发病原因主要与病原菌、环境条件和栽培管理等因素密切相关。\n" +
                        "\n" +
                        "病原菌：致病疫霉是引起番茄晚疫病的主要病原菌。该病菌以菌丝体在马铃薯病薯和番茄上越冬，成为翌年的侵染源。产生的孢子囊可通过雨水、灌溉水或风传播，通过伤口或直接侵入植物。\n" +
                        "环境条件：低温阴雨天气或昼夜温差大的地区或季节有利于病害的发生和流行。此外，相对湿度高于95%时，有利于孢子囊的形成和萌发。因此，在湿度大、温度适中的条件下，病害易迅速扩散。\n" +
                        "栽培管理：氮肥施用过多、栽植密度过大、通风不良等栽培管理措施不当，也可促进病害的流行。",
                "叶片：病斑大多先从叶尖或叶缘开始，呈暗绿色水渍状。随着病情的发展，病斑逐渐扩大并变为褐色。在空气湿度大时，病健交界处会产生一圈白色霉层。\n" +
                        "茎部：茎部受害后，会形成不规则形或条状病斑。病斑由水渍状变成暗褐色，稍凹陷，组织变软，导致植株萎蔫倒伏。\n" +
                        "果实：果实受害后，病斑不规则且稍凹陷，呈褐色或黑褐色。在潮湿条件下，果实病斑边缘会产生白色霉层。",
                "农业防治：\n" +
                        "选用抗病品种：不同番茄品种对晚疫病的抗病能力差异很大，需合理布局不同的抗病品种。\n" +
                        "加强栽培管理：轮作换茬，避免和马铃薯相邻种植。合理施肥和灌溉，避免氮肥施用过多。合理密植，降低农田湿度。加强田间卫生，及时清理病叶病株。\n" +
                        "化学防治：\n" +
                        "在疫病大流行时，喷洒农药是防治疫病最有效的方法。常用的农药有甲霜灵·锰锌、杀毒矾、百菌清和多菌灵等。喷洒时应注意药剂的浓度和喷洒时间，避免对植株造成药害。\n" +
                        "在病害发生初期，可及时喷药防治。药后闭棚增温，可提高防治效果。\n" +
                        "生物防治：\n" +
                        "利用生物制剂进行防治，如木霉菌、芽孢杆菌等生物菌剂对番茄晚疫病具有一定的防治效果。但需注意生物制剂的使用方法和注意事项。",
                "tomato_leaf_late_blight"
        ));
        plantlist.add(new AppleDomain("Tomato","煤霉病",
                "番茄煤霉病是由煤污尾孢（Cercospora fuligena Rold）引起的真菌性病害，广泛分布于中国各地，是番茄的常见病害之一。",
                "主要病原为煤污尾孢真菌，该病菌喜高温高湿的环境，适宜发病的温度范围为1538℃，最适发病环境为温度2532℃、相对湿度90%以上。",
                "主要危害番茄的叶片，也能危害茎和叶柄。叶片染病后，初在叶背产生褪绿色病斑，后扩展为近圆形黄褐色病斑。湿度大时，病斑背面出现黑色霉层，即病菌的分生孢子梗和分生孢子。茎和叶柄染病后，产生褪绿色斑，后被一层厚密的褐色霉层覆盖，病斑常绕茎和柄一周。",
                "1. 农业防治：\n" +
                        "\n" +
                        "实行轮作：发病地块实行与非茄科蔬菜2年以上轮作，以减少田间病菌来源。\n" +
                        "加强田间管理：提倡深沟高畦栽培，合理密植，开好排水沟系，雨后及时排水，降低地下水位。施足基肥，增施磷、钾肥，促使植株生长健壮，提高植株抗病能力。\n" +
                        "清洁田园：收获后及时清除病残体，带出田外深埋或烧毁，深翻土壤，加速病残体的腐烂分解。\n" +
                        "2. 化学防治：\n" +
                        "\n" +
                        "在发病初期开始喷药防治，每隔710天喷药1次，连续喷34次。药剂可选氟硅唑微乳剂、苯醚甲环唑微乳剂、嘧霉胺悬浮剂、啶酰菌胺水分散粒剂、戊唑醇悬浮剂等喷雾防治。具体用药量和使用方法请参照药剂说明书或咨询当地农业技术人员。",
                "tomato_mold_leaf"));
        plantlist.add(new AppleDomain("Potato","早疫病",
                "马铃薯早疫病，又称轮纹病，是马铃薯叶片上的主要病害之一，同时也可为害叶柄、茎和薯块。该病害在全球范围内广泛分布，对马铃薯的产量和品质造成严重影响。",
                "马铃薯早疫病的病原为半知菌亚门链格孢属茄链格孢菌（Alternaria solani）。病菌主要以菌丝体在病株残体或病薯中越冬，成为次年的初侵染源。在适宜的温湿度条件下，病菌的分生孢子通过风雨传播，落在马铃薯植株叶片表面后，从气孔、伤口或直接从表皮侵入，引发病害。",
                "叶片：叶片发病后，最初出现褐色圆形的小斑点，后逐渐扩大呈暗褐色至黑色的带有同心轮纹的病斑。病斑边缘有狭窄的褐色晕圈，多从植株下部叶片开始发生，逐渐向上部蔓延。当湿度大时，病斑表面会产生黑色霉层，即病菌的分生孢子梗和分生孢子。\n" +
                        "茎杆：茎杆染病后出现黑褐色病斑，呈长线条状，稍凹陷。后期病斑扩大成椭圆形，严重时上部叶片枯黄脱落，甚至整株枯死。\n" +
                        "薯块：薯块染病后，表皮产生大小不一、微凹陷的病斑，呈黑色。病健部明显，皮下组织呈褐色干腐状。",
                "农业防治：\n" +
                        "选育和推广抗病品种：选择具有水平抗性的品种和耐病品种进行种植。\n" +
                        "合理轮作：避免与茄科作物轮作，以减少病原菌的积累。\n" +
                        "加强田间管理：做好田园清洁，及时摘除病叶，收获后清除田间病残体，减少病原基数。同时，适当增施氮肥和钾肥，适时灌溉，培育健壮植株。\n" +
                        "药剂防治：\n" +
                        "在田间马铃薯底部叶片开始出现早疫病病斑时，及时施药进行防治。\n" +
                        "可选用的药剂有80%代森锌可湿性粉剂、250克/升嘧菌酯悬浮剂、70%丙森锌可湿性粉剂、500克/升氟啶胺悬浮剂、80%戊唑醇水分散粒剂等广谱性杀菌剂。\n" +
                        "施药间隔期为57天，连续防治35次。注意药剂的交替使用，避免产生抗药性。",
                "potato_leaf_early_blight"));
        plantlist.add(new AppleDomain("Tomato","黄化曲叶病",
                "番茄黄化曲叶病毒病是由番茄黄化曲叶病毒引起的、发生在番茄的病害。该病毒属于双生病毒科菜豆金色花叶病毒属病毒，在自然条件下只能由烟粉虱以持久方式传播，因此也被称为粉虱传双生病毒。它是一类具有孪生颗粒形态的植物DNA病毒，广泛分布于热带和亚热带地区，对烟草、番茄、南瓜、木薯、棉花等重要经济作物造成毁灭性危害。",
                "番茄黄化曲叶病毒病的主要传播媒介是烟粉虱。烟粉虱在对带病毒的植物进行取食时，会将病毒吸入体内，当烟粉虱再次取食时，TYLCV就会通过昆虫进入到新的植株体内，从而完成病毒传播。获毒后的烟粉虱将终生带毒，只要条件适宜，可周年传播。此外，带毒苗的异地输出也是导致种植地病毒传播的重要原因。",
                "番茄植株感染TYLCV后，会出现一系列明显的症状。在生长初期，植株生长迟缓或停滞，节间变短，明显矮化。叶片变小、变厚，叶质脆硬，有褶皱，向上卷曲，变形。叶片边缘至叶脉区域黄化，以植株上部叶片症状为典型，下部老叶症状不明显。在生长后期，植株坐果很少，果实变小，膨大速度极慢。成熟期的果实不能正常转色，着色不均匀，商品价值降低。",
                "农业防治：种植抗病品种是防治该病的关键措施之一。目前生产上推广的番茄品种多数为感病品种，因此应选用抗病性强的品种进行种植。同时，加强田间管理，合理施肥、浇水，提高植株的抗病性。\n" +
                        "物理防治：设置高密度防虫网（40目以上）是预防该病发生的最简单、也是最有效的方法之一。防虫网可以杜绝烟粉虱进入大棚，从而切断病毒的传播途径。此外，还可以在棚内于植株顶部略高处设黄板诱杀烟粉虱，每亩设40~60个。\n" +
                        "化学防治：在病毒发生初期，可以使用病毒抑制剂和生长促进剂配合施用，促进植株健壮生长，减少发病损失。同时，可以使用杀虫剂防治烟粉虱，但应注意选择对烟粉虱高效、对植株安全的药剂，并避免长期使用同一种药剂导致烟粉虱产生抗药性。\n" +
                        "生物防治：引人烟粉虱天敌，如瓢虫、草蛉等，以虫治虫，减少病毒传播。",
                "tomato_leaf_yellow_virus"));
        plantlist.add(new AppleDomain("Tomato","花叶病",
                "Tomato leaf mosaic virus，即番茄花叶病毒病，是一种危害番茄的重要病害，属于植物病毒病范畴。该病害由烟草花叶病毒（Tobacco mosaic virus，简称TMV）引起，该病毒具有广泛的寄主范围，能侵染包括烟草、番茄、辣椒等在内的多种茄科作物。",
                "番茄花叶病毒病的主要病因是烟草花叶病毒的侵染。该病毒能在活的植物体内越冬，也能在土壤中或土壤内的病残体上越冬，还可在种子上沾附的果肉里越冬。未充分腐熟的带毒肥料也是初侵染源。病毒主要通过汁液传播，蚜虫不传病毒。在植株生长期间，病毒可通过各种农事操作导致的接触传染进行扩散。此外，病、健株相互摩擦也会增加汁液传染机会；种植过密会加重病害；田间的蝗虫、烟青虫等咀嚼式口器的昆虫也可传播TMV病毒。与茄科植物连作地块，特别是番茄连作，病害会明显加重。土壤黏重、肥力不足，排水不畅，以及植株生长势弱、偏施氮肥等现象都可能导致发病。",
                "轻微花叶症状：叶片上出现绿色深浅不匀的斑驳，但叶片不变小，不畸形，植株不矮化，对产量影响不大。\n" +
                        "严重花叶症状：叶片黄绿明显凹凸不平，新叶片变小、细长、畸形、扭曲，叶脉变紫。植株矮化，花芽分化能力减退，大量落花落蕾。已坐果的果实果形细小，果小质劣呈花脸状。对产量影响很大，病株比健株减产10%~30%。",
                "农业防治：\n" +
                        "选用抗病品种，因地制宜合理种植。\n" +
                        "避免与烟草等茄科植物连作，减少病毒传播机会。\n" +
                        "采收完后及时清除病残体，在远离菜地、水源的地方烧毁或挖坑深埋。\n" +
                        "育苗前彻底清除苗床枯枝残叶和杂草；定植前深翻土壤，促使病残体腐烂。\n" +
                        "对病田里用过的工具、架材要进行消毒处理。\n" +
                        "栽培管理：\n" +
                        "选用排灌方便的田块，开好排水沟，降低地下水位，达到雨停无积水。大雨过后及时清理沟系，防止湿气滞留，降低田间湿度。\n" +
                        "施用酵素菌沤制的堆肥或腐熟的有机肥，不用带菌肥料。有机肥中不得含有植物病残体。\n" +
                        "采用配方施肥技术，适当增施磷钾肥，加强田间管理，培育壮苗，增强植株抗病力。\n" +
                        "高温干旱时应科学浇水，以提高田间湿度，减轻蚜虫、灰飞虱危害与传播其它病毒。也可在番茄行间采取铺草保湿等措施。严禁连续灌水和大水漫灌。\n" +
                        "药剂防治：\n" +
                        "发病初期可用20%病毒A2可湿性粉剂500600倍液进行叶面喷雾。每隔10天喷1次，全生长期喷24次，或视病情而定。\n" +
                        "早期及时防治蚜虫等传毒昆虫，减少病毒传播机会。",
                "tomato_leaf_mosaic_virus"));
        plantlist.add(new AppleDomain("Tomato","细菌性斑疹病",
                "番茄细菌性斑疹病是由丁香假单胞菌番茄致病变种引起的细菌性病害。该病主要危害番茄的叶、茎、花、叶柄和果实，尤其在叶缘及未成熟果实上症状明显。",
                "病原菌主要通过气孔、皮孔或伤口侵入，并借雨水、灌溉水及昆虫进行传播。高温高湿条件有利于病害的发生和流行。",
                "叶片上初现水渍状小点，后扩大为不规则病斑，边缘略隆起，病斑周围具窄的褪绿晕圈，病斑表面常略粗糙，有的呈不规则轮纹，且病斑多发生在叶缘。\n" +
                        "茎部病斑初呈椭圆形、褐色，后绕茎扩展，使茎秆变黑，病斑呈溃疡状，并导致植株折倒。\n" +
                        "花蕾受害后，在萼片上形成许多黑点，并扩展成黑色斑，易落花、落果。\n" +
                        "果实受害后，初现稍隆起的小斑点，后扩大为圆形或椭圆形病斑，边缘为黑色，中央灰白色，稍凹陷，病斑常连片或相互融合。",
                "选用抗病品种，加强田间管理，合理密植，及时整枝打杈，摘除病叶、病果，保持田间通风透光。\n" +
                        "发病初期及时喷洒药剂进行防治，如农用链霉素、新植霉素等。",
                "tomato_leaf_bacterial_spot"));
        plantlist.add(new AppleDomain("Corn","霉斑病",
                "Corn Gray leaf spot，中文名为玉米灰斑病，又称尾孢叶斑病、玉米霉斑病，是一种世界玉米产区普遍发生的叶部病害。该病由玉蜀黍尾孢菌（Cercospora zeae-maydis）引起，该病菌属于半知菌亚门尾孢属。除了玉米，它还可以侵染高粱、香茅等多种禾本科植物。",
                "玉米灰斑病的发生与流行主要受到环境因素的影响。病菌在干燥条件下可以在地表的病残体上安全越冬，成为第二年的初次侵染来源。而潮湿的环境条件，如降雨量大、相对湿度高、气温较低，则有利于病害的发生和流行。此外，种植方式、田间管理以及玉米品种的抗病性也会影响病害的发生。例如，轮作地、田间湿度大、采用免耕法或少耕法导致土表玉米残留物增多等情况下，病害发生会更严重。而品种间抗病性存在差异，有些品种对灰斑病有较好的抗性。",
                "玉米灰斑病主要危害叶片，也可侵染叶鞘和苞叶。发病初期，叶片上会出现褪绿小点，随着病害发展，病斑沿叶脉方向扩展，很少横向跨越叶脉，呈现长矩形条斑或两端不规则的条斑。病斑多为灰褐色或黄褐色，在田间湿度高时，病斑两面均可产生大量灰白色的霉层，即病菌的分生孢子梗和分生孢子。病斑多限于平行叶脉之间，大小通常在(420)×(25)毫米范围内。在感病品种上，病斑可能连片，导致叶片枯死。",
                "农业防治：\n" +
                        "选用对灰斑病有较好抗性的品种进行种植。\n" +
                        "通过秋翻春耙压低田间的初侵染菌源。\n" +
                        "采用间（套）作种植形式来改善田间小气候，降低田间的相对湿度，从而达到控制病害的发生和流行的目的。\n" +
                        "进行大面积轮作，加强田间管理，雨后及时排水，防止湿气滞留。\n" +
                        "收获后及时清除病残体，减少病菌的越冬场所。\n" +
                        "药剂防治：\n" +
                        "在发病初期，可以选用75%百菌清可湿性粉剂500倍液、50%多菌灵可湿性粉剂600倍液、40%克瘟散乳油800~900倍液、50%苯菌灵可湿性粉剂1500倍液或20%三唑酮乳油1000倍液等药剂进行喷雾防治。\n" +
                        "连续用药23次，每次用药间隔710天，防治效果较好。",
                "corn_gray_leaf_spot"));
        plantlist.add(new AppleDomain("Tomato","轮纹病",
                "Tomato Early blight，即番茄早疫病，又称为轮纹病，是危害番茄的重要病害之一。该病由茄链格孢菌（Alternaria solani）侵染引起，属于半知菌亚门、链格孢属真菌。此病害在中国全境都有发生，不论保护地还是露地栽培的番茄均可受害，且除番茄外，还能为害马铃薯、茄子和辣椒等茄科蔬菜作物。",
                "番茄早疫病的病原菌主要以菌丝和分生孢子随病株残体在土壤中越冬，也可附着在种子上越冬。菌丝在干叶中存活1年以上，分生孢子在室温下可存活17个月。在受侵染的茎叶中也可形成厚垣孢子，在-31~27℃下可保持生活力7个月左右。翌年产生新的分生孢子，成为初次侵染来源。\n" +
                        "\n" +
                        "带菌种子是病害远距离传播的有效媒介，灌溉水、农事作业和昆虫也能传播，特别是雨滴飞溅，把病菌带到植株上，常引起下部叶片先发病。在条件适宜时，病菌侵入寄主后只需23天就可形成病斑，再经过34天，即可产生大量的分生孢子，引起多次重复的再侵染。",
                "番茄早疫病主要危害叶片，也可危害茎和果实。叶片被害，初呈深褐色或黑色圆形或椭圆形的小斑点，渐扩大，达1~3厘米，边缘深褐色，中央灰褐色，有同心轮纹。天气潮湿时，病斑上长有黑色霉，即分生孢子梗和分生孢子。病害常从植株下部叶片开始，渐次向上蔓延。发病严重时，植株下部叶片完全枯死。\n" +
                        "\n" +
                        "茎部病斑多数在分枝处发生，灰褐色，椭圆形，稍凹陷，也有同心轮纹。发病严重时，可造成断枝。幼苗常在接近地面的茎部发病，病斑黑褐色。病株后期茎杆部常布满黑褐色的病斑，因此有“乌脚膀”之称。\n" +
                        "\n" +
                        "果实上病斑多发生在蒂部附近和有裂缝的地方，形或近圆形，褐色或黑褐色，稍凹陷，也有同心轮纹，其上长有黑色霉。病果常提早脱落",
                "农业防治：\n" +
                        "选用抗病品种，如迪丽雅、欧缇丽、凯旋158等。\n" +
                        "加强栽培管理，施足基肥，适时追肥，或使用蔬菜专用肥，做到盛果期不脱肥，提高寄主抗病性。\n" +
                        "合理密植，及时绑架、整枝和打底叶，利于通风透光。\n" +
                        "保护地番茄重点抓生态防治，控制温、湿度。露地番茄注意雨后及时排水，清除落叶、残枝和病果，结合整地搞好田园卫生。\n" +
                        "重病田实行与非茄科作物2~3年轮作。\n" +
                        "种子处理：\n" +
                        "采用2%武夷霉素浸种，或用种子重量0.4%的50%克菌丹可湿性粉剂拌种。\n" +
                        "用52℃温汤浸种30分钟，或用70℃干热处理72小时法进行处理（注意采后对种子给予一定的后熟转化期）。\n" +
                        "药剂防治：\n" +
                        "在发病初期，可选用苯醚甲环唑、丙森锌、戊唑醇、异菌脲、多抗霉素、代森锌、代森锰锌等杀菌剂进行喷雾防治。\n" +
                        "具体制剂和施药剂量需根据病情和药剂说明进行，注意交替用药，避免产生抗药性。",
                "tomato_early_blight_leaf"
        ));
        plantlist.add(new AppleDomain("Apple Scab Leaf","褐纹病",
                "Apple Scab Leaf，通常被翻译为苹果斑点落叶病，又称苹果褐纹病，是由苹果链格孢的强毒株系侵染所引起的病害。该病害在世界各苹果产区均有发生，其中日本、朝鲜半岛等地发生较重。苹果斑点落叶病主要危害苹果叶片，同时也对叶柄、一年生枝条和果实造成威胁，严重影响果树的生长和果实的产量与质量。",
                "病原菌的存在：苹果链格孢的强毒株系是引起该病害的主要病原菌。这些病原菌在落叶、枝条等病残体上越冬，第二年春季产生病菌孢子，通过气流、风雨等传播方式侵染叶片。\n" +
                        "气候条件适宜：高温、高湿、多雨、多雾的气候条件有利于病原菌的繁殖和扩散。特别是在气温达到28℃以上且降雨较多的情况下，病害的流行速度会明显加快。\n" +
                        "果园管理不当：果园的土壤管理、施肥、修剪等措施不当，会导致树势衰弱，增加病害的发生风险。例如，土壤缺乏深翻改土等管理措施，施肥过程中忽视生物肥、有机肥的补充，修剪不当导致伤口过多等。",
                "叶片：新梢的嫩叶上首先出现褐色至深褐色的圆形斑点，直径约为2-3毫米。病斑周围常有紫色晕圈，边缘清晰。随着气温的上升和病害的发展，病斑可扩大到5-6毫米，并可能融合成不规则形状。在空气潮湿时，病斑背面会产生黑绿色至暗黑色的霉状物，即病菌的分生孢子梗和分生孢子。中后期病斑常被其他腐生菌寄生，变为灰白色，并长出小黑点（腐生菌的分生孢子器）。部分病斑会脱落或穿孔。\n" +
                        "叶柄：在夏秋季节，病菌会侵染叶柄并产生暗褐色的凹陷斑。染病叶片会随即脱落或从叶柄病斑处折断。\n" +
                        "枝条：在徒长枝或一年生枝条上会产生褐色或灰褐色的病斑。病斑边缘清晰，有时伴有裂缝。轻度发病的枝条上只有皮孔开裂。\n" +
                        "果实：果实上的病斑有黑点型、斑点型和果点褐变型三种类型。其中斑点型最为常见，初期在幼果果面上产生黑色发亮的小斑点或锈斑。盛夏季节被侵染的果实会呈现褐色瘪病状，直径可达2-5厘米，并易在病健交界处开裂。",
                "加强果园管理：改善果园的通风透光条件，合理修剪枝条以减少伤口数量。同时加强土壤管理，深翻改土并补充生物肥、有机肥等养分以提高树势和抗病力。\n" +
                        "清除病源：在秋末冬初及时剪除病枝并清除残枝落叶，集中烧毁以减少初侵染源。\n" +
                        "药剂防治：在病害发生初期及时喷施药剂进行防治。常用药剂包括安泰生70%可湿性粉剂、70%代森锰锌可湿性粉剂、10%多抗霉素可湿性粉剂、50%扑海因可湿性粉剂等。要注意多药交替使用以避免病原菌产生抗药性。同时根据降雨情况和病害发展程度灵活调整施药次数和药剂浓度。",
                "apple_scab_leaf"
        ));
        plantlist.add(new AppleDomain("Tomato","斑点病",
                "Tomato Septoria leaf spot，中文名为番茄斑枯病，又称鱼目斑病、斑点病、白星病，是番茄叶部的一种主要病害，在中国南北各地均有分布，且各地均有发生。该病害主要危害番茄的叶片，同时也能侵害茎、花萼、叶柄和果实，对番茄的产量造成严重影响，一般能降低产量20%~30%，严重时甚至可降低50%。",
                "番茄斑枯病的病原为番茄壳针孢菌（Septoria lycopersici Speg.），属于半知菌亚门真菌。这种病菌以菌丝和分生孢子器的形式在病残体、多年生茄科杂草上或附着在种子上越冬，成为第二年的初侵染源。分生孢子通过雨水反溅到番茄植株上，萌发后从气孔侵入，并在寄主细胞间隙蔓延，以分枝的吸胞穿入细胞内吸取养分，导致组织破坏或死亡。病菌在温暖多雨的季节蔓延迅速，特别是在日均气温15℃以上、遇阴雨天气，尤其是雨后转晴时，病害容易流行。此外，土壤缺肥、植株生长势衰弱、种植过密、通风透光差等因素也会加重病害的发生。",
                "番茄斑枯病在番茄的整个生长期都可能发病，但结果初期发病较为集中。病害通常先从下部老叶开始，然后逐渐向上发展。叶片发病初期，叶背面出现水渍状小圆斑，随后在叶子两面出现圆形或近圆形的病斑，边缘深褐色，中部灰白色，稍凹陷，病斑直径通常在3mm左右。在灰白色部分会长出小黑点，即病原菌的分生孢子器。严重时，病斑会联合形成大的枯斑，有时病部组织坏死穿孔，甚至中下部叶片干枯或脱落。茎和叶柄上的病斑近圆形或椭圆形，略凹陷，褐色，其上也有黑色小粒点。果实上的病斑则呈圆形，褐色，但相对较少见。",
                "种子消毒：在播种前，对种子进行晾晒和消毒处理。可以用50℃温水浸种30分钟，并不断搅拌热水，然后取出晾干催芽播种。\n" +
                        "农业防治：加强田间管理，合理施肥，增施磷钾肥，增强植株抗性。及时打掉下部病叶、老叶，带出田外集中销毁。采用与非茄科作物进行3~4年以上的轮作方式，避免连作。在育苗时，可选择3年内未种过茄科类菜的土壤育苗，以避免苗期发病。同时，应保持田间通风透光，降低湿度。\n" +
                        "化学防治：在发病初期，可以喷洒杀菌剂进行防治。常用农药有50%百菌清可湿性粉剂600倍液、64%杀毒矾可湿性粉剂500倍液、58%甲霜灵·锰锌可湿性粉剂500倍液等。每710天喷1次，连喷23次。",
                "tomato_septoria_leaf_spot"));
        plantlist.add(new AppleDomain("Bell pepper leaf spot","叶斑病",
                "Bell Pepper Leaf Spot，通常指的是发生在甜椒（Bell Pepper）上的一种叶斑病，这种病害对甜椒的生长和产量有显著影响。以下是对该病害的详细介绍，包括病害原因、病害症状以及病害防治指导。",
                "Bell Pepper Leaf Spot病害主要由真菌引起，这些真菌在适宜的环境条件下，如温暖、潮湿的天气，会迅速繁殖并侵染甜椒植株。病原菌可能通过气流、雨水或昆虫等媒介传播，从植株的气孔或伤口侵入，导致病害的发生。此外，土壤中的病原菌残留也可能成为下一年的初侵染源。",
                "Bell Pepper Leaf Spot病害的症状主要表现在叶片上，初期叶片上可能出现水渍状的斑点，随后逐渐扩大并变为褐色或黑色，边缘清晰。病斑的形状可能因病原菌的种类而异，有的呈圆形，有的则呈不规则形。随着病情的加重，病斑可能相互融合，导致叶片大面积枯死。在潮湿的条件下，病斑上还可能产生灰白色霉层或分生孢子，进一步加剧病害的传播。",
                "农业防治：\n" +
                        "合理轮作和间作，避免连作导致的病原菌积累。\n" +
                        "加强田间管理，及时清除病株和病残体，减少病原菌的越冬场所。\n" +
                        "合理施肥，增施有机肥和磷钾肥，提高植株的抗病性。\n" +
                        "生物防治：\n" +
                        "利用生物制剂进行防治，如使用苏云金芽孢杆菌等生物农药，对病原菌进行抑制。\n" +
                        "保护和利用天敌，如瓢虫等，对害虫进行生物控制，减少害虫对植株的伤害，从而降低病害的发生几率。\n" +
                        "化学防治：\n" +
                        "在病害发生初期，及时喷洒杀菌剂进行防治。常用的杀菌剂包括代森锰锌、多菌灵、甲基托布津等。\n" +
                        "注意交替用药，避免病原菌产生抗药性。\n" +
                        "严格按照农药使用说明进行喷洒，确保用药安全和效果。\n" +
                        "物理防治：\n" +
                        "对于小面积的病害发生，可以采用人工摘除病叶的方法，减少病原菌的扩散。\n" +
                        "对土壤进行消毒处理，如使用高温蒸汽或化学消毒剂，以减少土壤中的病原菌数量。",
                "bell_pepper_leaf_spot"));
        plantlist.add(new AppleDomain("Grapevine","黑腐病",
                "葡萄黑腐病，又称Grapevine black rot，是一种由真菌引起的病害。该病害在葡萄的叶片、叶柄、果实、新梢和卷须上均有发生，对葡萄的产量和品质造成严重影响。该病害在全球各葡萄产区均有分布，尤其在气候湿润、温度适宜的地区更为严重。",
                "葡萄黑腐病的主要病原菌为葡萄球座菌（Guignardia bidwellii），属于子囊菌门真菌。该病原菌以子囊壳在僵果上过冬，也可通过分生孢子进行传播。在适宜的水分和湿度条件下，病原菌的孢子会萌发并侵入葡萄植株，导致病害的发生。此外，果园管理不当，如排水不良、通风透光性差、修剪不及时等，也会加重病害的发生。",
                "叶片：叶脉间出现红褐色近圆形小斑，直径约2~3mm。病斑扩大后，中央变为灰白色，外部为褐色，边缘为黑色，并生出许多黑色小粒点，沿病斑排列成环状。\n" +
                        "叶柄：叶柄染病后，会出现褐色病斑，并可能逐渐扩大，导致叶柄枯死。\n" +
                        "果实：果实染病初期，出现紫褐色小斑点，逐渐扩大后，边缘褐色，中央灰白色略凹陷。随着病害的发展，果实会软腐、干缩，变为黑色或灰蓝色僵果，棱角明显，病果上布满清晰的小黑粒点，即病菌的分生孢器或子囊壳。\n" +
                        "新梢：新梢染病后，会出现深褐色椭圆形微凹陷斑，其上也生有许多黑色小粒点。",
                "加强果园管理：改善果园通风透光条件，及时排水修剪，降低园内湿度。增施有机肥，提高葡萄植株的抗病能力。铲除行间杂草，控制结果量，保持葡萄植株的健康生长。\n" +
                        "彻底清园：秋冬季彻底修剪和清园工作，翻耕果园土壤，减少病原菌的越冬基数。芽前喷波美35度石硫合剂或45%晶体石硫合剂2130倍液，以杀灭越冬病原菌。\n" +
                        "摘除病果病枝：发病季节及时摘除并销毁病果、病枝梢，减少田间再侵染源。\n" +
                        "药剂防治：在开花前、谢花后和果实膨大期等关键时期，喷洒波尔多液、多菌灵、托布津等药剂进行防治。隔1015天喷1次，连续防治23次。注意药剂的交替使用，避免病原菌产生抗药性。",
                "grape_leaf_black_rot"));
        plantlist.add(new AppleDomain("Tomato","二斑叶螨",
                "番茄二斑叶螨（Two-spotted spider mite），又称二点叶螨，是番茄等蔬菜作物上的重要害虫之一。它以刺吸式口器吸食植物汁液，导致植物叶片出现黄化、卷曲、脱落等症状，严重时甚至造成植株死亡。该病害在全球范围内广泛分布，对番茄等作物的生产造成了严重威胁。",
                "气候因素：温暖、干燥的气候条件有利于番茄二斑叶螨的繁殖和扩散。在高温、低湿的环境下，番茄二斑叶螨的繁殖速度加快，危害程度加重。\n" +
                        "栽培管理：不合理的栽培管理措施，如过度密植、氮肥施用过多等，会导致番茄植株生长衰弱，抗虫能力下降，从而加重番茄二斑叶螨的危害。\n" +
                        "天敌减少：天敌是控制番茄二斑叶螨数量的重要因素。然而，由于农药的过度使用等原因，天敌数量减少，使得番茄二斑叶螨的危害进一步加剧。",
                "叶片黄化：受害叶片出现黄化现象，颜色变淡，失去光泽。\n" +
                        "叶片卷曲：受害叶片边缘向上卷曲，形成“船底状”，严重时整个叶片卷曲成筒状。\n" +
                        "叶片脱落：受害严重的叶片会脱落，导致植株生长受阻，产量下降。\n" +
                        "蜘蛛网状纹络：在受害叶片的背面，可见到由番茄二斑叶螨吐丝形成的蜘蛛网状纹络。",
                "农业防治：加强栽培管理，合理密植，避免过度施用氮肥，提高植株的抗虫能力。及时清除田间杂草和病株，减少害虫的滋生环境。\n" +
                        "生物防治：保护和利用天敌，如瓢虫、草蛉等，以天敌控制番茄二斑叶螨的数量。\n" +
                        "化学防治：在害虫发生初期，可选用高效、低毒、低残留的农药进行喷雾防治。常用的农药有阿维菌素、螺螨酯等。注意轮换用药，避免害虫产生抗药性。同时，要严格按照农药使用说明进行操作，确保用药安全。\n" +
                        "物理防治：可利用黄色黏虫板诱捕成虫，减少害虫数量。此外，还可以采用高温闷棚等方法杀灭害虫及虫卵。",
                "tomato_two_spotted_spider_mites_leaf"));
        review = findViewById(R.id.view_on_detect);
        //reverseLayout布局翻转
        review.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapter = new PlantdocAdapter(plantlist);
        review.setAdapter(adapter);

    }
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.plantdoc_detect);
        imageView2=(ImageView) findViewById(R.id.imageView);
        imageView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(detect_plant.this, select_view.class));

                bitmap.recycle();
                finish();
            }
        });
        initRecycleView();

        boolean ret_init = yolov5ncnn.Init(getAssets());
        if (!ret_init)
        {
            Log.e("detect_plant", "yolov5ncnn Init failed");
        }

        imageView = (ImageView) findViewById(R.id.PlantView);

        Button buttonImage = (Button) findViewById(R.id.PlantImage);
        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, SELECT_IMAGE);
            }
        });

        Button buttonDetect = (Button) findViewById(R.id.PlantDetect);
        buttonDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (yourSelectedImage == null)
                    return;

                Yolov5Ncnnplant.Obj[] objects = yolov5ncnn.Detect(yourSelectedImage, false);

                showObjects(objects);
            }
        });

        Button buttonDetectGPU = (Button) findViewById(R.id.buttonDetect_plant);
        buttonDetectGPU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (yourSelectedImage == null)
                    return;

                Yolov5Ncnnplant.Obj[] objects = yolov5ncnn.Detect(yourSelectedImage, true);

                showObjects(objects);
            }
        });
    }

    private void showObjects(Yolov5Ncnnplant.Obj[] objects)
    {
        if (objects == null)
        {
            imageView.setImageBitmap(bitmap);
            return;
        }

        // draw objects on bitmap
        Bitmap rgba = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        final int[] colors = new int[] {
                Color.rgb( 54,  67, 244),
                Color.rgb( 99,  30, 233),
                Color.rgb(176,  39, 156),
                Color.rgb(183,  58, 103),
                Color.rgb(181,  81,  63),
                Color.rgb(243, 150,  33),
                Color.rgb(244, 169,   3),
                Color.rgb(212, 188,   0),
                Color.rgb(136, 150,   0),
                Color.rgb( 80, 175,  76),
                Color.rgb( 74, 195, 139),
                Color.rgb( 57, 220, 205),
                Color.rgb( 59, 235, 255),
                Color.rgb(  7, 193, 255),
                Color.rgb(  0, 152, 255),
                Color.rgb( 34,  87, 255),
                Color.rgb( 72,  85, 121),
                Color.rgb(158, 158, 158),
                Color.rgb(139, 125,  96)
        };

        Canvas canvas = new Canvas(rgba);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);

        Paint textbgpaint = new Paint();
        textbgpaint.setColor(Color.WHITE);
        textbgpaint.setStyle(Paint.Style.FILL);

        Paint textpaint = new Paint();
        textpaint.setColor(Color.BLACK);
        textpaint.setTextSize(26);
        textpaint.setTextAlign(Paint.Align.LEFT);

        for (int i = 0; i < objects.length; i++)
        {
            paint.setColor(colors[i % 19]);

            canvas.drawRect(objects[i].x, objects[i].y, objects[i].x + objects[i].w, objects[i].y + objects[i].h, paint);

            // draw filled text inside image
            {
                String text = objects[i].label + " = " + String.format("%.1f", objects[i].prob * 100) + "%";

                float text_width = textpaint.measureText(text);
                float text_height = - textpaint.ascent() + textpaint.descent();

                float x = objects[i].x;
                float y = objects[i].y - text_height;
                if (y < 0)
                    y = 0;
                if (x + text_width > rgba.getWidth())
                    x = rgba.getWidth() - text_width;

                canvas.drawRect(x, y, x + text_width, y + text_height, textbgpaint);

                canvas.drawText(text, x, y - textpaint.ascent(), textpaint);
            }
        }

        imageView.setImageBitmap(rgba);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();

            try
            {
                if (requestCode == SELECT_IMAGE) {
                    bitmap = decodeUri(selectedImage);

                    yourSelectedImage = bitmap.copy(Bitmap.Config.ARGB_8888, true);

                    imageView.setImageBitmap(bitmap);
                }
            }
            catch (FileNotFoundException e)
            {
                Log.e("detect_plant", "FileNotFoundException");
                return;
            }
        }
    }

    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException
    {
        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 640;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);

        // Rotate according to EXIF
        int rotate = 0;
        try
        {
            ExifInterface exif = new ExifInterface(getContentResolver().openInputStream(selectedImage));
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        }
        catch (IOException e)
        {
            Log.e("detect_plant", "ExifInterface IOException");
        }

        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}