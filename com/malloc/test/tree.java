package com.malloc.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.ontopia.topicmaps.core.TopicIF;
import net.ontopia.topicmaps.core.TopicMapBuilderIF;
import net.ontopia.topicmaps.core.TopicMapIF;
import net.ontopia.topicmaps.impl.basic.InMemoryTopicMapStore;
import net.ontopia.topicmaps.xml.XTMTopicMapReader;
import net.ontopia.topicmaps.xml.XTMTopicMapWriter;

public class tree {

    private static final String XTM = "hoho.xtm";

    //
    private int size = 0;

    public int getSize() {
        return this.size;
    }

    public int sizeIncrease() {
        return ++size;
    }

    public int sizeDecrease() {
        return --size;
    }

    //
    static TopicMapIF aTopicmap = null;
    static XTMTopicMapReader aReader = null;
    static TopicMapBuilderIF aBuilder = null;

    public static node nodeBigBro = null;
    public TopicIF topicBigBro = null;

    ArrayList<node> an = new ArrayList<node>();

    public tree() throws IOException {

        new XTMTopicMapWriter(XTM).write(new InMemoryTopicMapStore()
                .getTopicMap());

        aReader = new XTMTopicMapReader(new File("hoho.xtm"));
        aTopicmap = aReader.read();
        aBuilder = aTopicmap.getBuilder();

        topicBigBro = aBuilder.makeTopic();
        aBuilder.makeTopicName(topicBigBro, "BigBro");
        nodeBigBro = new node(-1, "BigBro", topicBigBro, null, null);
        sizeIncrease();

        TopicIF topic1 = aBuilder.makeTopic();
        aBuilder.makeTopicName(topic1, "#1");
        node node1 = new node(1, "#1", topic1, null, null);
        // node1.parentNode = nodeBigBro;
        nodeBigBro.childList.add(node1);
        sizeIncrease();

        TopicIF topic2 = aBuilder.makeTopic();
        aBuilder.makeTopicName(topic2, "@2");
        node node2 = new node(2, "@2", topic2, null, null);
        // node2.parentNode = nodeBigBro;
        nodeBigBro.childList.add(node2);
        sizeIncrease();

        TopicIF topic3 = aBuilder.makeTopic();
        aBuilder.makeTopicName(topic3, "$3");
        node node3 = new node(3, "$3", topic3, null, null);
        // node3.parentNode = nodeBigBro;
        nodeBigBro.childList.add(node3);
        sizeIncrease();

        TopicIF topic21 = aBuilder.makeTopic();
        aBuilder.makeTopicName(topic21, "@21");
        node node21 = new node(21, "@21", topic21, null, null);
        // node21.parentNode = node2;
        node2.childList.add(node21);
        sizeIncrease();

        TopicIF topic22 = aBuilder.makeTopic();
        aBuilder.makeTopicName(topic22, "@22");
        node node22 = new node(22, "@22", topic22, null, null);
        // node22.parentNode = node2;
        node2.childList.add(node22);
        sizeIncrease();

        TopicIF topic31 = aBuilder.makeTopic();
        aBuilder.makeTopicName(topic31, "$31");
        node node31 = new node(31, "$31", topic31, null, null);
        // node31.parentNode = node3;
        node3.childList.add(node31);
        sizeIncrease();

        TopicIF topic32 = aBuilder.makeTopic();
        aBuilder.makeTopicName(topic32, "$32");
        node node32 = new node(32, "$32", topic32, null, null);
        // node32.parentNode = node3;
        node3.childList.add(node32);
        sizeIncrease();

        TopicIF topic321 = aBuilder.makeTopic();
        aBuilder.makeTopicName(topic321, "$321");
        node node321 = new node(321, "$321", topic321, null, null);
        // node321.parentNode = node32;
        node32.childList.add(node321);
        sizeIncrease();

        TopicIF topic311 = aBuilder.makeTopic();
        aBuilder.makeTopicName(topic311, "$311");
        node node311 = new node(311, "$311", topic311, null, null);
        // node311.parentNode = node31;
        node31.childList.add(node311);
        sizeIncrease();

        TopicIF topic4 = aBuilder.makeTopic();
        aBuilder.makeTopicName(topic4, "%4");
        node node4 = new node(4, "%4", topic4, null, null);
        // node4.parentNode = nodeBigBro;
        nodeBigBro.childList.add(node4);
        sizeIncrease();

        new XTMTopicMapWriter(XTM).write(aTopicmap);

    }

    public boolean addNode(int pIndex, String pName, int addIndex,
            String addName) throws IOException {

        int treeSize = getSize();
        int newTreeSize = 0;

        TopicIF tt = aBuilder.makeTopic();
        aBuilder.makeTopicName(tt, addName);

        node n = new node(addIndex, addName, tt, null, null);
        sizeIncrease();

        new XTMTopicMapWriter("hoho.xtm").write(aTopicmap);

        return newTreeSize == treeSize + 1 ? true : false;

    }

    public boolean deleteNode(int deleteIndex, String deleteName)
            throws IOException {

        int treeSize = getSize();
        int newTreeSize = 0;
        int deteleSize = 0;

        // sizeDecrease();deteleSize++;

        new XTMTopicMapWriter("hoho.xtm").write(aTopicmap);

        return newTreeSize == treeSize - deteleSize ? true : false;

    }

    public void FindNode(int num, node r) throws IOException {

        node newNode = new node();
        String s = "bb";
        // 以传入的节点为根，深度优先遍历
        if (r.childList != null && r.childList.size() != 0) {
            System.out.println("if r.childList is not null!");
            for (int i = 0; i < r.childList.size(); i++) {
                System.out.println("==" + r.childList.get(i).index + " ,"
                        + r.childList.get(i).name);
                if (r.childList.get(i).index == num) {
                    System.out.println("find " + r.childList.get(i).index
                            + " ," + r.childList.get(i).name);
                    // return r.childList.get(i);
                    return;
                } else {
                    System.out.println("else " + r.childList.get(i).index
                            + " ," + r.childList.get(i).name);

                    FindNode(num, r.childList.get(i));
                }

                // for (node n : r.childList) {
                // System.out.println("n.index = " + n.index + "; n.name = "
                // + n.name);
                // newNode = n;
                // if (n.index != num) {
                // FindNode(num, newNode);
                // } else {
                // System.out.println("==" + (newNode.index == num));
                // return newNode;
                // }
            }
        }

        // 没找到
        // return newNode;
    }

    public node ha(int num, node r) {
        node returnNode = null;
        int i = 0;

        if (r.index == num) {
            System.out.println("find " + r.childList.get(i).index + " ,"
                    + r.childList.get(i).name);
            returnNode = r;
        } else if (!r.childList.isEmpty()) {
            i = 0;
            while (returnNode == null && i < r.childList.size()) {
                System.out.println("esle " + r.childList.get(i).index + " ,"
                        + r.childList.get(i).name);
                returnNode = ha(num, r.childList.get(i));
                ++i;
            }
            
//            for (int j = 0; j < r.childList.size() && returnNode == null; ++j){
//                System.out.println("esle " + r.childList.get(j).index + " ,"
//                        + r.childList.get(j).name);
//                returnNode = ha(num, r.childList.get(j));
//            }
        }

        return returnNode;
    }

    public String he(int num, node r) {
        String s = null;

        for (int i = 0; i < r.childList.size(); i++) {
            s = r.childList.get(i).name;
            System.out.println("*** " + r.childList.get(i).index + " " + s);
            if (r.childList.get(i).index == num) {
                System.out.println("find " + r.childList.get(i).index + " ,"
                        + r.childList.get(i).name);
                return s;
            } else if (r.childList.get(i).index != num) {
                System.out.println("esle " + r.childList.get(i).index + " ,"
                        + r.childList.get(i).name);
                ha(num, r.childList.get(i));
            }

        }

        return s;
    }

    public static void main(String[] args) throws IOException {
        tree t = new tree();
        // t.FindNode(32, nodeBigBro);
        // System.out.println("\nmain " + t.FindNode(21, nodeBigBro).name);

        int a = 3;
        System.out.println("finding : " + a + "\n");
        t.ha(3, nodeBigBro);
//        System.out.println(t.ha(a, nodeBigBro).index);

//        t.ha(1, nodeBigBro);
//        System.out.println("===");
//        t.ha(2, nodeBigBro);
//        System.out.println("===");
//        t.ha(3, nodeBigBro);
//        System.out.println("===");
//        t.ha(31, nodeBigBro);
//        System.out.println("===");
//        t.ha(311, nodeBigBro);
//        System.out.println("===");
//        t.ha(32, nodeBigBro);
//        System.out.println("===");
//        t.ha(321, nodeBigBro);
//        System.out.println("===");
//        t.ha(4, nodeBigBro);
//        System.out.println("===");
        
        // System.out.println("$$$$1 " + aTopicmap.getTopics().size());
        //
        // System.out.println("$$$$2 " + aTopicmap.getTopics().size());
        //
        // System.out.println("$$$$3 " + aTopicmap.getTopics().size());
        //
        // System.out.println("$$$$4 " + aTopicmap.getTopics().size());

        new XTMTopicMapWriter("hoho.xtm").write(aTopicmap);

        System.out.println("\n tree DONE");
    }
}
