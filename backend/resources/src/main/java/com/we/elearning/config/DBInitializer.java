package com.we.elearning.config;

import com.we.elearning.templates.entities.Template;
import com.we.elearning.templates.repositories.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DBInitializer implements CommandLineRunner {

   private final TemplateRepository templateRepository;
    @Override
    public void run(String... args) throws Exception {
        Template nuxt = Template.builder()
                .name("🪄 Nuxt Starter Templates")
                .description("Quickly get started with a minimal Nuxt starter template!")
                .imageUrl("https://i0.wp.com/segwitz.com/wp-content/uploads/2021/06/vuejs-development-malaysia.jpeg")
                .githubUrl("'https://github.com/nuxt/starter.git'")
                .build();

        Template vueJs = Template.builder()
                .name("Vue.js starter template")
                .description(" starter template for Vue.js projects")
                .imageUrl("https://a.storyblok.com/f/88751/x/0095dfd328/nuxt-logo.svg")
                .githubUrl("https://github.com/villeristi/vue.js-starter-template.git")
                .build();

        Template nextJSPayment = Template.builder()
                .name("Next.js Subscription Payments Starter")
                .description("The all-in-one starter kit for high-performance SaaS applications.")
                .imageUrl("https://isotropic.co/wp-content/uploads/2022/04/react.png")
                .githubUrl("https://github.com/vercel/nextjs-subscription-payments.git")
                .build();

        Template nextJsLanding = Template.builder()
                .name("Landing Page Template built with Next JS 12+, Tailwind CSS 3 and TypeScript")
                .description("🚀 Landing Page theme written in Next.js, Tailwind CSS and TypeScript ⚡️ Made with developer experience first: Next.js, TypeScript, ESLint, Prettier, Husky, Lint-Staged, VSCode, Netlify, PostCSS, Tailwind CSS.Clone this project and use it to create your own Next.js project. You can check a Next js templates demo.")
                .imageUrl("https://isotropic.co/wp-content/uploads/2022/04/nextjs.png")
                .githubUrl("https://github.com/ixartz/Next-JS-Landing-Page-Starter-Template.git")
                .build();

        templateRepository.saveAll(List.of(
                vueJs,
                nuxt,
                nextJSPayment,
                nextJsLanding
        ));
    }
}