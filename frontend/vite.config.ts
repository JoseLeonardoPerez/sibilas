import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import { VitePWA } from "vite-plugin-pwa";

export default defineConfig({

  base: "/sibilas/",

  plugins: [

    react(),

    VitePWA({

      registerType: "autoUpdate",

      manifest: {

        name: "Sibilas",

        short_name: "Sibilas",

        description:
          "Astrología, carta natal, oráculo y guía espiritual",

        theme_color: "#020617",

        background_color: "#020617",

        display: "standalone",

        orientation: "portrait",

        start_url: "/sibilas/",

        icons: [

          {
            src: "/sibilas/icon-96.png",
            sizes: "96x96",
            type: "image/png"
          },

          {
            src: "/sibilas/icon-128.png",
            sizes: "128x128",
            type: "image/png"
          },

          {
            src: "/sibilas/icon-152.png",
            sizes: "152x152",
            type: "image/png"
          },

          {
            src: "/sibilas/icon-180.png",
            sizes: "180x180",
            type: "image/png"
          },

          {
            src: "/sibilas/icon-192.png",
            sizes: "192x192",
            type: "image/png"
          },

          {
            src: "/sibilas/icon-512.png",
            sizes: "512x512",
            type: "image/png"
          }

        ]
      }
    })
  ]
});