// Tencent is pleased to support the open source community by making ncnn available.
//
// Copyright (C) 2020 THL A29 Limited, a Tencent company. All rights reserved.
//
// Licensed under the BSD 3-Clause License (the "License"); you may not use this file except
// in compliance with the License. You may obtain a copy of the License at
//
// https://opensource.org/licenses/BSD-3-Clause
//
// Unless required by applicable law or agreed to in writing, software distributed
// under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
// CONDITIONS OF ANY KIND, either express or implied. See the License for the
// specific language governing permissions and limitations under the License.

package com.tencent.yolov5ncnn;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Adapters.AppleAdapter;
import Adapters.WheatAdapter;
import domain.AppleDomain;
import domain.WheatDomain;

public class MainActivity extends Activity
{
    private static final int SELECT_IMAGE = 2 ;
    private ImageView imageView,imageView2;
    private Bitmap bitmap = null;
    private Bitmap yourSelectedImage = null;

    private YoloV5Ncnn yolov5ncnn = new YoloV5Ncnn();
    private RecyclerView.Adapter adapter;
    private RecyclerView review;
    public void initRecycleView(){
        ArrayList<AppleDomain>  applelist = new ArrayList<>();
        applelist.add(new AppleDomain("apple","褐斑病",
                "苹果褐斑病，又称绿缘褐斑病，是由苹果双壳菌（Diplocarpon mali Harada et Sawamura）侵染所引起的一种病害，它主要发生在苹果树上，对苹果树的生产造成严重影响。以下是对苹果褐斑病的详细描述及其发病原因的归纳：",
                "气候条件：\n" +
                        "高温多雨是苹果褐斑病发生和流行的主要条件。雨水多、湿度大有利于病原菌的繁殖和传播，同时高温也缩短了病害的潜育期。\n" +
                        "春季雨水早、雨量大，夏季雨量偏多，冬季气候潮湿的年份，此病发生较早且很严重。\n" +
                        "果园管理：\n" +
                        "果园管理不当，如地势低洼、排水灌溉不良、通风透光不好等，容易形成高温高湿的小气候环境，有利于病原菌的滋生和蔓延。\n" +
                        "用药不及时或不得当，也会导致病害的严重发生。\n" +
                        "树势与品种：\n" +
                        "树势衰弱、抗病力差的苹果树更容易受到苹果褐斑病的侵害。\n" +
                        "不同苹果品种对苹果褐斑病的抗性也存在差异，一些易感品种在相同条件下发病更为严重。\n" +
                        "病原基数：\n" +
                        "病原菌数量多、基数高也是导致苹果褐斑病发生严重的原因之一。上年病叶上的病原菌在落叶中越冬后，次年成为初侵染源，增加了病害的初侵染压力。",
                "褐斑病在叶片上主要有针芒型、同心轮纹型和混合型3种。①针芒型。病斑呈针芒放射形向外扩展，斑点小且多，形状不固定，病斑上有很多隆起的小黑点，后期叶片渐黄，病部周围及背部仍保持绿色。②同心轮纹型。发病初期叶面出现黄褐色小点，逐渐扩大为圆形，中心黑褐色，周围黄色，病斑周围有绿色晕圈，直径1-2.5厘米，病斑中心出现轮纹状黑色小点，病斑背部中央深褐色，四周浅褐色，无明显边缘。③混合型。病斑暗褐色，较大。近圆形或数个不规则病斑连接在一起形成不规则形，直径0.3-3厘米，其上散生黑色小点，但轮纹状不明显，后期病叶变黄，病斑边缘仍保持绿色，时间长了病斑中间呈灰白色。上述3种症状一般难以区分，品种不同发病症状不同。3种症状共同特点：发病后期病斑中央变黄，周围仍保持绿色晕圈，且病叶容易脱落",
                "一、农业防治\n" +
                        "加强栽培管理：\n" +
                        "合理修剪：根据树体的生长情况及时合理修剪，疏除过密无用的枝条，保持果园良好的通风透光条件，促进果树的光合作用，提高树势和抗病能力。\n" +
                        "科学施肥：依树势、树龄、产量等合理施肥，增强树体营养，提高抗病能力。\n" +
                        "合理灌溉与排水：对地势低洼、土质黏重、地下水位较高的果园，要合理灌溉并及时排水，降低果园内空气湿度，减少病菌的繁殖和侵染。\n" +
                        "清园工作：\n" +
                        "认真做好清园工作，减轻病害发生机率。秋季清园时，要及时清除果园内的落叶、枯老树皮、病枝、枯枝、杂草等，并带出果园集中销毁，减少越冬病菌的数量。\n" +
                        "结合冬季修剪，剪除树上的僵果病叶等，进一步降低病原基数。\n" +
                        "二、化学防治\n" +
                        "喷药保护：\n" +
                        "在病害发生前或初期，及时喷施杀菌剂进行保护。药剂可选择戊唑醇、丙环唑、宁南霉素、多抗霉素、农抗120等。喷药时要兼顾叶片背面、树体内膛及树冠下部叶片，力求均匀周到。\n" +
                        "褐斑病多发生在空气炎热潮湿的5-6月，因此在这个时期要加强喷药保护，每隔15天喷一次，连续喷药3-4次。\n" +
                        "特别注意在雨季或连续降雨后，要及时补喷农药，以防病害的扩散和蔓延。\n" +
                        "交替用药：\n" +
                        "为了避免病菌产生抗药性，建议交替使用不同类型的杀菌剂。如苯甲嘧菌酯、啶氧菌酯、苯甲丙环唑、苯醚甲环唑等，可以与戊唑醇等药剂交替使用。\n" +
                        "结合叶面肥：\n" +
                        "在喷药的同时，可以结合喷施叶面肥如磷酸二氢钾等，以提高叶片的抗病能力和光合效率。",
                "cercospora1"));
        applelist.add(new AppleDomain("apple","花叶病"
                ,"苹果花叶病是由苹果花叶病毒、土拉苹果花叶病毒或李坏死环斑病毒中的苹果花叶株系侵染所引起的病害。这些病毒在苹果树上寄生并繁殖，导致叶片出现各种异常症状，进而影响果树的生长和果实的产量与品质。",
                "苹果花叶病的发生原因主要包括以下几个方面：\n" +
                        "\n" +
                        "病毒传播：病毒主要通过嫁接传播和汁液传播。病接穗及砧木的嫁接传播，以及病健根的自然接触，甚至在病树上用过的刀、剪、锯等工具都可能成为传播病毒的媒介。\n" +
                        "土肥管理不当：果园土壤管理不善，缺乏深翻改土等土壤管理措施，施肥过程中忽视生物肥、有机肥的补充，导致土壤养分失衡，树体衰弱，从而增加了病毒病的发生风险。\n" +
                        "负载量过重：苹果树挂果过多，树体负载量过重，加上采摘不及时，对树体营养补充不足，削弱了树势，增加了病毒病的发生风险。",
                "苹果花叶病的症状主要表现在叶片上，因病情轻重不同，症状变化较大，主要有以下几种类型：\n" +
                        "\n" +
                        "斑驳型：病叶上出现大小不等、形状不规则、边缘清晰的鲜黄色病斑，后期病斑处常易枯死。\n" +
                        "花叶型：病斑不规则，有较大的浅绿和深绿相间的色变，边缘不清晰。\n" +
                        "条斑型：病叶上会沿叶脉失绿黄化，并延及附近的叶肉组织，有时仅主脉和支脉黄化，变色部分较宽；有时主脉、支脉、小脉都会呈现较狭窄的黄化，使整叶呈网纹状。\n" +
                        "环斑型：病叶上会产生鲜黄色的环状或近似环状的病纹斑，环内仍成绿色。\n" +
                        "镶边型：病叶边缘的锯齿及其附近发生黄化，从而在叶边缘形成一条变色镶边，近似缺钾症状。\n" +
                        "在自然条件下，各症状类型多在同一树上混合发生。病重时，叶片易变色、环死、扭曲、皱缩，有时还可导致早期落叶。",
                "针对苹果花叶病的防治，可以从以下几个方面入手：\n" +
                        "\n" +
                        "选用脱毒苗木：建园时选用脱毒苗木，接穗从无病毒的树上剪取，砧木选用种子繁殖的实生苗，避免使用根蘖苗，尤其是病株的根蘖苗。\n" +
                        "拔除病株：对丧失结果能力的重病树和未结果的幼病树，应及时刨除，并对土壤进行消毒处理，以防病毒传播到周围树上。\n" +
                        "增强树体抗病能力：通过秋施基肥、增施有机质、合理修剪等措施增强树势，提高树体对病毒病的抵抗能力。\n" +
                        "加强树体管理：对病株加强土肥水管理，避免剪、锯等工具在使用过程中传毒，并及时防治传毒昆虫如蚜虫、木虱等。\n" +
                        "药剂防治：在发病初期可喷洒盐酸吗琳胍·乙酸铜或壳聚糖等药剂进行防治，每隔10～15天喷一次，连喷2～3次。\n" +
                        "长期防治：苹果树病毒病的防治需要坚持多年防治，才能逐渐减轻病情，降低危害。" ,
                "mosaic"));
        applelist.add(new AppleDomain("apple","斑点落叶病",
                "Apple Alternaria病害，即苹果斑点落叶病，又称苹果褐纹病，是一种由苹果链格孢的强毒株系侵染所引起的病害。该病害在世界各苹果产区均有发生，主要分布于中国、美国、新西兰和津巴布韦等国，其中日本、朝鲜半岛发生较重。",
                "苹果斑点落叶病的病原为苹果链格孢的强毒株系，属半知菌类真菌。病菌以菌丝和分生孢子在病落叶上越冬，第2年苹果展叶期借雨露雾水萌发，随风雨或气流传播，侵染幼嫩叶片。病菌从侵入到发病需要24~72小时。生长期田间病叶不断产生分生孢子，借风雨传播蔓延，进行再侵染。",
                "叶片：新梢的嫩叶上产生褐色至深褐色圆形斑，直径2~3毫米。病斑周围常有紫色晕圈，边缘清晰。随着气温的上升，病斑可扩大到5~6毫米，呈深褐色，有时数个病斑融合，成为不规则形状。空气潮湿时，病斑背面产生黑绿色至暗黑色霉状物，为病菌的分生孢子梗和分生孢子。中后期病斑常被叶点霉真菌等腐生，变为灰白色，中间长出小黑点，为腐生菌的分生孢子器。有些病斑脱落、穿孔。夏、秋季高温高湿，病菌繁殖量大，发病周期缩短，秋梢部位叶片病斑迅速增多，一片病叶上常有病斑10~20个，影响叶片正常生长，常造成叶片扭曲和皱缩，病部焦枯，易被风吹断，残缺不全。\n" +
                        "枝干：在徒长枝或一年生枝条上产生病斑褐色或灰褐色，芽周变黑，凹陷坏死，直径2~6毫米，边缘裂开。发病轻时，仅皮孔稍隆起。\n" +
                        "果实：果面的病斑有4种类型，即黑点锈斑型、疮痂型、斑点型和黑点褐变型。黑点锈斑型表现为果面上的黑色至黑褐色小斑点，略具光泽，微隆起，小点周围及黑点脱落处呈锈斑状；疮痂型为灰褐色疮痂状斑块，病健交界处有龟裂，病斑不剥离，仅限于病果表皮，但有时皮下浅层果肉可成为干腐状木栓化；斑点型为果点为中心形成褐色至黑褐色圆形或不规则形小斑点，套袋果摘袋后病斑周围有花青素沉积，呈红色斑点；黑点褐变型为果点及周围变褐，周围花青素沉积明显，呈红晕状。",
                "选用抗病品种：如丰县红富士及富士系、元帅系和乔纳金等。\n" +
                        "清除初侵染源：秋末冬初剪除病枝，清除落叶，集中烧毁，以减少初侵染源。\n" +
                        "加强栽培管理：夏季剪除徒长枝，减少后期侵染源，改善果园通透性。低洼地、水位高的果园要注意排水。合理施肥，增强树势，提高抗病力。\n" +
                        "药剂防治：在发病前（5月中旬左右落花后）开始喷药，药剂可选用1:2:200倍式波尔多液或10%世高水分散粒剂20002500倍液、70%代森锰锌可湿性粉剂400600倍液等。生产上可交替使用，隔1020天一次，共防34次。各地应根据发病时期和气候条件确定喷药次数和时间。",
                "apple_alternaria"));
        applelist.add(new AppleDomain("apple","瘤蚜",
                "苹果瘤蚜，异名苹果卷叶蚜、苹叶蚜虫，学名Myxus malisuctus Matsumura，属于同翅目蚜科。它主要为害苹果、海棠、山楂等果树，严重影响果树的生长和产量。",
                "苹果瘤蚜以孤雌生殖方式繁殖，每年可发生10余代。它主要以卵的形式越冬，果树发芽至展叶期，越冬卵孵化，开始为害果树。",
                "叶片受害：被害叶片正面凸凹不平，光合功能降低。受害重的叶片从边缘向叶背纵卷，严重者呈绳状。\n" +
                        "幼果受害：果面出现许多略凹陷的红斑。\n" +
                        "新梢受害：被害重的新梢叶片全部卷缩，枝梢细弱，渐渐枯死，影响果实生长发育和着色。",
                "加强果园管理，合理修剪，增强树势。\n" +
                        "冬季清除果园内的落叶、杂草等，减少虫源。\n" +
                        "在果树发芽前，喷洒石硫合剂等农药进行防治。\n" +
                        "发现虫害时，及时喷洒吡虫啉、啶虫脒等农药进行防治",
                "apple_aphid"));
        applelist.add(new AppleDomain("apple","桧胶锈菌",
                "桧胶锈病是由桧胶锈菌（Gymnosporangium juniperi-virginianae）引起的一种植物病害，主要影响东方红柏（Juniperus virginiana）和桧属（Juniperus）的一些种类，以及苹果及苹果属（Malus）植物。此锈菌的生活史需经桧属及苹果两种寄主才能完成。",
                "桧胶锈病的发病原因是桧胶锈菌的侵染。锈菌在桧属植物上形成菌丝体越冬，翌春形成褐色的冬孢子角。冬孢子柄被有胶质，遇降雨或空气极潮湿时胶化膨大，冬孢子萌发产生大量担孢子，随风传播到苹果树上。锈菌侵染苹果树的叶片、果实、新梢等，形成性孢子器和性孢子、锈孢子器和锈孢子。锈孢子成熟后，再随风传播到桧柏上，侵害桧柏枝条，以菌丝体在桧柏病部越冬。",
                "枝干：叶柄、果柄和嫩枝发病后，病部为橙黄色，隆起呈纺锤形，上面形成性孢子器和锈孢子器；后期病部凹陷，龟裂，易折断。在桧柏小枝上形成绿褐色至赭褐色瘿瘤，圆形或肾形，径达数厘米。\n" +
                        "叶片：初期叶片正面产生橙黄色有光泽的小斑点，随后发展成直径为0.51.0厘米的橙黄色圆形病斑，病斑边缘常呈红色，稍肥厚。严重时，一片叶子上可有十几个病斑。发病12周后，病斑表面密生鲜黄色细小点粒，即性孢子器。\n" +
                        "果实：幼果发病后，多在萼洼附近形成直径约1厘米的病斑，前期橙黄色，后期变成黑色，中间产生性孢子器，周围长出毛状的锈孢子器。病果生长停滞，多呈畸形，往往提前脱落。",
                "铲除寄主：及时清除果园附近的桧柏等转主寄主，减少病原菌的来源。\n" +
                        "药剂防治：春季在桧类植物上喷洒杀菌药剂，以消灭越冬的菌丝体和冬孢子；夏季在苹果树上喷洒药剂，以防治锈菌的侵染和扩散。\n" +
                        "加强果园管理：合理修剪，增强树势，提高果树的抗病能力。同时，注意果园的通风透光，降低湿度，减少病害的发生。",
                "apple_rust"));
        review = findViewById(R.id.view_on_apple);
        //reverseLayout布局翻转
        review.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapter = new AppleAdapter(applelist);
        review.setAdapter(adapter);
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initRecycleView();
        imageView2=(ImageView) findViewById(R.id.imageView);
        imageView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, select_view.class));

                bitmap.recycle();
                finish();
            }
        });
        boolean ret_init = yolov5ncnn.Init(getAssets());
        if (!ret_init)
        {
            Log.e("MainActivity", "yolov5ncnn Init failed");
        }

        imageView = (ImageView) findViewById(R.id.AppleView);

        Button buttonImage = (Button) findViewById(R.id.AppleImage);
        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, SELECT_IMAGE);
            }
        });

        Button buttonDetect = (Button) findViewById(R.id.AppleDetect);
        buttonDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (yourSelectedImage == null)
                    return;

                YoloV5Ncnn.Obj[] objects = yolov5ncnn.Detect(yourSelectedImage, false);

                showObjects(objects);
            }
        });

        Button buttonDetectGPU = (Button) findViewById(R.id.buttonDetect_apple);
        buttonDetectGPU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (yourSelectedImage == null)
                    return;

                YoloV5Ncnn.Obj[] objects = yolov5ncnn.Detect(yourSelectedImage, true);

                showObjects(objects);
            }
        });
    }

    private void showObjects(YoloV5Ncnn.Obj[] objects)
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
                Log.e("MainActivity", "FileNotFoundException");
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
            Log.e("MainActivity", "ExifInterface IOException");
        }

        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
