# Based on :: https://github.com/the-nix-way/dev-templates

{
  description = "YAMS by @gersonfaneto";

  inputs.nixpkgs.url = "https://flakehub.com/f/NixOS/nixpkgs/0.1.*.tar.gz";

  outputs = { self, nixpkgs }:
    let
      javaVersion = 17;

      supportedSystems = [ "x86_64-linux" "aarch64-linux" "x86_64-darwin" "aarch64-darwin" ];

      forEachSupportedSystem = f: nixpkgs.lib.genAttrs supportedSystems (system: f {
        pkgs = import nixpkgs { inherit system; overlays = [ self.overlays.default ]; };
      });
    in
    {
      overlays.default =
        final: prev:
        let
          jdk = prev."jdk${toString javaVersion}";
        in
        {
          maven = prev.maven.override { jdk_headless = jdk; };
          gradle = prev.gradle.override { java = jdk; };
          lombok = prev.lombok.override { inherit jdk; };
        };

      devShells = forEachSupportedSystem ({ pkgs }: {
        default = pkgs.mkShell {
          packages = with pkgs; [
            # Java
            jdk17
            maven
            gradle

            # C/C++
            gcc

            # Libraries
            zlib
            ncurses
            patchelf

            # JavaFX
            javaPackages.openjfx17
            glib
            gtk3
            gtk4
            xorg.libXxf86vm
          ];

          LOCALE_ARCHIVE = "${pkgs.glibcLocales}/lib/locale/locale-archive";
          LD_LIBRARY_PATH = "${pkgs.libGL}/lib:${pkgs.gtk3}/lib:${pkgs.glib.out}/lib:${pkgs.xorg.libXtst}/lib";

          shellHook =
            let
              loadLombok = "-javaagent:${pkgs.lombok}/share/java/lombok.jar";
              prev = "\${JAVA_TOOL_OPTIONS:+ $JAVA_TOOL_OPTIONS}";
            in
            ''
              export JAVA_TOOL_OPTIONS="${loadLombok}${prev}"
            '';
        };
      });
    };
}
