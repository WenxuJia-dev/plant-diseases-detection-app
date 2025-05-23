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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Adapters.WheatAdapter;
import domain.WheatDomain;

public class detect_act extends Activity {

    //spinner

    private static final int SELECT_IMAGE = 1;
    private ImageView imageView,imageView2;
    private Bitmap bitmap = null;
    private Bitmap yourSelectedImage = null;
    private Yolov5NcnnDetector yolov5ncnn=new Yolov5NcnnDetector();
    private RecyclerView.Adapter adapter;
    private RecyclerView review;

    public void initRecycleView(){
        ArrayList<WheatDomain> items=new ArrayList<>();
        items.add(new WheatDomain("wheat","黄矮病——yellow dwarf",
                "小麦黄矮病，又称黄矮病、黄斑病，是一种由真菌引起的小麦病害。这种病害主要影响小麦的生长和产量，严重时甚至会导致小麦死亡。因此，了解小麦黄矮病的发病原因、症状、防治方法等相关知识，对于保障小麦产量具有重要意义。",
                "小麦黄矮病的发病原因主要是真菌感染。这种真菌属于子囊菌门，主要通过孢子繁殖。在高温多湿的气候条件下，真菌孢子容易在小麦上萌发，导致病害的发生。此外，土壤肥力不足、播种密度过大、植株生长不良等因素也会加重病害的发生程度。",
                "小麦黄矮病的症状主要表现为植株矮小、叶片黄化、叶脉间出现黄斑等。病情严重时，植株会出现枯萎、死亡的现象。此外，受感染的小麦籽粒也会出现黄色斑点，影响小麦的品质和产量.",
                "1.选用抗病品种：种植抗病性较强的小麦品种，可以有效降低病害的发生风险。目前市场上已有多个抗病性较好的小麦品种可供选择。\n" +
                        "\n" +
                        "2.加强田间管理：合理控制播种密度，避免过密种植；保持田间排水畅通，防止积水；合理施肥，提高植株抗病能力。\n" +
                        "\n" +
                        "3.化学防治：在病害初期，可使用含有硫磺、铜剂等成分的杀菌剂进行喷雾防治。如发现病情严重，应及时采取措施，避免病害扩散。\n" +
                        "\n" +
                        "4.生物防治：利用拮抗菌、杀虫剂等生物制剂进行防治，既环保又安全。但需注意选择适合当地气候条件的生物制剂，并按照推荐剂量进行使用。\n" +
                        "\n" +
                        "5.农业措施：采用轮作制度，避免连作；及时清除病残体，减少病菌越冬场所；加强病虫害监测，及时发现和控制病害。\n" +
                        "\n" +
                        "综上所述，小麦黄矮病是一种严重影响小麦产量的病害。要想有效防治这一病害，需要从选用抗病品种、加强田间管理、化学防治、生物防治等多方面入手，综合施策，才能确保小麦的健康生长和高产稳产。",
                "yellow"));
        items.add(new WheatDomain("wheat","锈病——rust","锈病是由真菌中的锈菌寄生引起的一类植物病害。危害植物的叶、茎和果实。锈菌一般只引起局部侵染，受害部位可因孢子积集而产生不同颜色的小疱点或疱状、杯状、毛状物，有的还可在枝干上引起肿瘤、粗皮、丛枝、曲枝等症状，或造成落叶、焦梢、生长不良等。严重时孢子堆密集成片，植株因体内水分大量蒸发而迅速枯死。",
                "小麦隐匿柄锈菌侵染所引起的",
                "小麦叶锈病主要危害小麦叶片，产生疱疹状病斑，很少危害叶鞘和茎秆。 [3]叶片受害，产生圆形或近圆形橘红色疹状病斑，即夏孢子堆。夏孢子堆比秆锈病的小，较条锈病的大。夏孢子堆表皮破裂后，散出黄褐色粉末，即夏孢子。夏孢子堆较小，不规则散生，多发生在叶片正面。有时病菌可穿透叶片，在叶片两面同时形成夏孢子堆。后期在叶背面散生暗褐色至深褐色、椭圆形的冬孢子堆，成熟时不破裂。有时也为害叶鞘，但很少为害茎秆或穗。",
                "中国对小麦叶锈病的防治主要采用综合防治的措施来进行防治，主要以抗病品种合理布局为主，采用加强栽培措施和药剂防治为辅的综合防治措施。达到全面的控制小麦叶锈菌夏孢子的扩展，使小麦叶锈病得到有效地控制。",
                "rust"));
        items.add(new WheatDomain("wheat","白粉病",
                "小麦白粉病是由禾本科布氏白粉菌引起、发生在小麦上的病害。主要为害叶片，严重时叶鞘、茎秆、穗部均会受到侵染。",
                "小麦白粉病是由禾本科布氏白粉菌引起、发生在小麦上的病害。",
                "小麦白粉病主要发生于叶片上，也可发生于植株叶鞘、茎秆和穗部。一般叶正面病斑较叶背面多，下部叶片较上部叶片病害重。叶面出现1-2毫米的白色霉点，后逐渐扩大为近圆形至椭圆形白色霉斑，霉斑表面有一层白粉状霉层，逐渐扩大并相互联合，呈长椭圆形。较大的霉斑，严重时可覆盖叶片大部，甚至全部，霉层厚度可达2毫米左右，并逐渐呈粉状，白粉遇有外力或振动立即飞散。后期霉层逐渐由白色变为灰白色至浅褐色，上生针头大小黑色小颗粒，是病菌的闭囊壳。霉层下的叶片组织初期无明显变化，随后褪绿、发黄以至枯死。发病严重时，小麦植株弱小，不抽穗或抽出的穗短小。",
                "小麦白粉病的防治方法主要以农业防治和化学防治为主。因地制宜，种植抗病品种。多施堆肥或腐熟有机肥，增施磷、钾肥，提高植株抗病力。及时浇水抗旱，雨后要及时排水，防止湿气滞留。自生麦苗越夏地区，冬小麦秋播前要及时清除自生麦，可大大减少秋苗菌源。再结合化学药剂防治。",
                "medew"));
        items.add(new WheatDomain("wheat","枯病",
                "小麦叶枯病是由小麦叶枯病菌引起的、发生在 小麦 和 黑麦 的一种病害。",
                "",
                "小麦叶枯病主要为害小麦叶片和叶鞘，有时也为害茎秆及穗部。其症状因环境条件不同而有差异。一般约在小麦拔节至抽穗期开始，在叶片上于叶脉间最初出现淡绿色至黄色纺锤形病斑，以后逐渐扩展并相互愈合成不规则形淡褐色大斑块，上面散生黑色小点，即病菌的分生孢子器。有时病斑呈黄色并连成条纹状，叶脉为黄绿色，乍看似如小麦黄矮病，但其条纹边缘为波浪形，且贯通全叶。严重时黄叶部分呈水渍状长条，并左右扩展，使叶片变成枯白色，上生小黑点（分生孢子器）。病叶一般从下部叶片开始向上发展，病斑从叶鞘向茎秆部扩展，并侵染穗部颖壳使其变为枯白色。病叶有时很快变黄、变薄、下垂，但不很快枯死。有的病叶病斑不大，但叶尖全部干枯，而后逐渐扩展",
                "(一)农业防治\n" +
                        "\n" +
                        "选种抗(耐)病品种;适期精量播种,防止冬前生长量大、侵染旱;加强肥水管理,沟系配套,排灌\n" +
                        "\n" +
                        "通畅;平衡施肥,不偏施氮肥,控制群体数量。搞好麦田除草工作。\n" +
                        "\n" +
                        "(二)化学防治\n" +
                        "\n" +
                        "1.药剂拌种\n" +
                        "\n" +
                        "60g/L戊唑醇悬浮种衣剂,每10kg小麦种子用药5~6.67mL,加水200mL拌种;或釆用30g/L苯醚甲环唑悬浮种衣剂,每10kg种子用药20~30mL,加水200mL拌种,可兼治黑穗病。\n" +
                        "\n" +
                        "2.药剂喷雾\n" +
                        "\n" +
                        "(1)防治适期。小麦拔节初期,当病株率达10%时开始第一次防治,以后隔7~10d根据病情决定是否需要再次防治。\n" +
                        "\n" +
                        "(2)防治用药。井冈霉素、丙环唑、己唑醇、戊唑醇等单剂及其复配剂。纹枯病严重田块,在拔节期要采取“大剂量、大水量、提前泼浇或对水粗喷雾”的方法,确保药液淋到根、茎基等发病部位,切实提高防治效果。如每公顷用5%井冈霉素水剂3750mL对水750kg喷洒。",
                "blight"));

        review = findViewById(R.id.view_on_detect);
        //reverseLayout布局翻转
        review.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapter = new WheatAdapter(items);
        review.setAdapter(adapter);
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detect_layout);

        imageView2=(ImageView) findViewById(R.id.imageView);
        imageView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                bitmap.recycle();
                finish();
            }
        });
        initRecycleView();
        boolean ret_init = yolov5ncnn.Init(getAssets());
        if (!ret_init)
        {
            Log.e("detect_act", "yolov5ncnn Init failed");
        }

        imageView = (ImageView) findViewById(R.id.WheatView);

        Button buttonImage = (Button) findViewById(R.id.WheatImage);
        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, SELECT_IMAGE);
            }
        });

        Button buttonDetect = (Button) findViewById(R.id.WheatDetect);
        buttonDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (yourSelectedImage == null)
                    return;

                Yolov5NcnnDetector.Obj[] objects = yolov5ncnn.Detect(yourSelectedImage, false);

                showObjects(objects);
            }
        });

//        Button buttonDetectGPU = (Button) findViewById(R.id.buttonDetect_wheat);
//        buttonDetectGPU.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                if (yourSelectedImage == null)
//                    return;
//
//                Yolov5NcnnDetector.Obj[] objects = yolov5ncnn.Detect(yourSelectedImage, true);
//
//                showObjects(objects);
//            }
//        });
    }

    private void showObjects(Yolov5NcnnDetector.Obj[] objects)
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
    //获得result
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
                Log.e("detect_act", "FileNotFoundException");
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
            Log.e("detect_act", "ExifInterface IOException");
        }

        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}