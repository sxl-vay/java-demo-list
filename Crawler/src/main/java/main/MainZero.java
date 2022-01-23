package main;

import POJO.OptionsEntity;
import exception.CoordinateException;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;
import util.GetTCPLink;
import util.JsonAndMainHelper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainZero implements Runnable {



    private static Logger log = Logger.getLogger(MainZero.class.getClass());

    public static void main(String[] args) {
        JsonAndMainHelper.init();
        //System.out.println("enterInfo = " + JsonAndMainHelper.enterInfoForRun);
        JsonAndMainHelper.createDir();
        JsonAndMainHelper.threadProcessing();

        //主线程协助完成多余的任务。
        int start = JsonAndMainHelper.START + (JsonAndMainHelper.THREAD_COUNT * JsonAndMainHelper.countForRun);
        int end = JsonAndMainHelper.END;
        int i = start;
        for (; i<=end; i++) {
            JsonAndMainHelper.downloadPic4Thread(i, end);
        }
    }

    @Override
    public void run() {
        long id = Thread.currentThread().getId();
        int l = (int) id % JsonAndMainHelper.THREAD_COUNT;
        int start = JsonAndMainHelper.START + (l * JsonAndMainHelper.countForRun);
        int end = start + JsonAndMainHelper.countForRun;
        System.out.println("run did");
        for (; start < end && start < JsonAndMainHelper.END; start++) {
            JsonAndMainHelper.downloadPic4Thread(start, end);
        }
    }

}
