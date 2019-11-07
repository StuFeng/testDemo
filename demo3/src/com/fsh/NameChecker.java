/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.fsh;

import static javax.tools.Diagnostic.Kind.WARNING;

import java.util.EnumSet;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner8;

/**
 * @author fengsihan
 * @date 2019-11-01 21:08
 * @desc 名称检查
 * 程序名称规范的编译器插件
 * 如果程序命名不规范，将会输出一个编译器的 WARING 信息
 */
public class NameChecker {

    private final Messager messager;

    NameCheckScanner nameCheckScanner = new NameCheckScanner();

    public NameChecker(ProcessingEnvironment processingEnv) {
        messager = processingEnv.getMessager();
    }

    public void checkNames(Element element) {
        nameCheckScanner.scan(element);
    }

    private class NameCheckScanner extends ElementScanner8<Void, Void> {

        /**
         * 此方法检查 java 类
         *
         * @param e
         * @param aVoid
         *
         * @return
         */
        @Override
        public Void visitType(TypeElement e, Void aVoid) {

            scan(e.getTypeParameters(), aVoid);
            // 检查驼峰
            checkCamelCase(e, true);
            super.visitType(e, aVoid);

            return null;
        }

        /**
         * 检查方法命名是否合法
         *
         * @param e
         * @param aVoid
         *
         * @return
         */
        @Override
        public Void visitExecutable(ExecutableElement e, Void aVoid) {
            if (e.getKind() == ElementKind.METHOD) {
                Name name = e.getSimpleName();
                if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
                    messager.printMessage(WARNING, "一个普通方法" + name + "不应当与类型重复，避免与构造函数产生混淆", e);
                    checkCamelCase(e, false);
                }
            }
            super.visitExecutable(e, aVoid);
            return null;
        }

        /**
         * 检查变量名是否合法
         *
         * @param e
         * @param aVoid
         *
         * @return
         */
        @Override
        public Void visitVariable(VariableElement e, Void aVoid) {
            // 如果这个 Variable 是枚举或常量，则按大写命名检查，否则按照驼峰命名法规则检查
            if (e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null ||
                    heuristicallyConstant(e)) {
                checkAllCaps(e);
            } else {
                checkCamelCase(e, false);
            }
            return null;
        }

        /**
         * 第一个字符必须是大写的，其余可以是下划线或大写字母
         * @param e
         */
        private void checkAllCaps(VariableElement e) {
            String name = e.getSimpleName().toString();
            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);

            if (!Character.isUpperCase(firstCodePoint)){
                conventional = false;
            } else {
                /**
                 * 是否为下划线
                 */
                boolean previousUnderscore = false;
                int cp = firstCodePoint;
                for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                    cp = name.codePointAt(i);
                    if (cp == (int)'_'){
                        if (previousUnderscore){
                            conventional = false;
                            break;
                        }
                        previousUnderscore = true;
                    }else{
                        previousUnderscore = false;
                        if (!Character.isUpperCase(cp) && !Character.isDigit(cp)){
                            conventional = false;
                            break;
                        }
                    }
                }
            }

            if (!conventional){
                messager.printMessage(WARNING, "常量" + name + "应当全部以大写和下划线命名", e);
            }
        }

        private boolean heuristicallyConstant(VariableElement e) {
            //
            if (e.getEnclosingElement().getKind() == ElementKind.INTERFACE) {
                return true;
            } else if (e.getKind() == ElementKind.FIELD && e.getModifiers()
                    .containsAll(EnumSet.of(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL))) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * 检查传入的 Element 是否符合驼峰式命名法，如果不符合，则输出警告信息
         *
         * @param e
         * @param initialCaps 首字母是否大写
         */
        private void checkCamelCase(Element e, boolean initialCaps) {
            String name = e.getSimpleName().toString();
            /**
             * 前一个字母是否为大写
             */
            boolean previousUpper = false;
            /**
             * 是否遵守规范
             */
            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);
            if (Character.isUpperCase(firstCodePoint)) {
                previousUpper = true;
                if (!initialCaps) {
                    messager.printMessage(WARNING, "名称" + name + "应当以小写字母开头", e);
                    return;
                }
            } else if (Character.isLowerCase(firstCodePoint)) {
                if (initialCaps) {
                    messager.printMessage(WARNING, "名称" + name + "应当以大写字母开头", e);
                    return;
                }
            } else {
                conventional = false;
            }

            if (conventional) {
                int cp = firstCodePoint;
                for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                    cp = name.codePointAt(i);
                    if (Character.isUpperCase(cp)) {
                        if (previousUpper) {
                            conventional = false;
                            break;
                        }
                        previousUpper = true;
                    }else{
                        previousUpper = false;
                    }
                }
            }

            if (!conventional){
                messager.printMessage(WARNING, "名称" + name + "应当符合驼峰命名法", e);
            }
        }
    }
}
