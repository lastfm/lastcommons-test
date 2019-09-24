package fm.last.commons.test.extensions;

import java.io.File;

import org.junit.jupiter.api.extension.Extension;

import fm.last.commons.test.core.DataFolderUtils;

class RootDataFolderExtension extends BaseDataFolder implements Extension {

  private File folder;

  public RootDataFolderExtension() {
    this(new String[] {});
  }

  public RootDataFolderExtension(String... children) {
    folder = DataFolderUtils.buildRootDataFolder(children);
  }

  @Override
  public File getDataFolder() {
    return folder;
  }
}
