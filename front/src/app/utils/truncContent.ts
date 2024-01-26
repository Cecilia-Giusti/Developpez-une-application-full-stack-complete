export function truncContent(content: string, maxWords: number): string {
  const words = content.split(/\s+/);

  if (words.length > maxWords) {
    return words.slice(0, maxWords).join(' ') + '...';
  }

  return content;
}
